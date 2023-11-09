package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.iamhere.models.SortData
import com.progressterra.ipbandroidapi.api.iamhere.models.StatusResult
import com.progressterra.ipbandroidapi.api.iamhere.models.TypeVariantSort
import com.progressterra.ipbandroidview.entities.DatingTarget
import com.progressterra.ipbandroidview.entities.toDatingTarget
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.ToastedException
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface AvailableTargetsUseCase {

    suspend operator fun invoke(): Result<List<DatingTarget>>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val service: ImhService, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : AvailableTargetsUseCase, AbstractTokenUseCase(
        obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke(): Result<List<DatingTarget>> = withToken { token ->
            val result = service.targetList(
                token,
                body = FilterAndSort(
                    listFields = emptyList(),
                    sort = SortData(
                        fieldName = "dateAdded",
                        variantSort = TypeVariantSort.DESC
                    ),
                    searchData = "",
                    skip = 0,
                    take = 100
                )
            )
            if (result.result?.status != StatusResult.SUCCESS) {
                throw ToastedException(result.result?.message ?: "")
            }
            result.dataList?.map { it.toDatingTarget() } ?: emptyList()
        }
    }
}