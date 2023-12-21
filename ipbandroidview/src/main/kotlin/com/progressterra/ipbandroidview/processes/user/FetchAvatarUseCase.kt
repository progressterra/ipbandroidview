package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidapi.api.ipbmediadata.IPBMediaDataService
import com.progressterra.ipbandroidapi.api.ipbmediadata.models.FieldForFilter
import com.progressterra.ipbandroidapi.api.ipbmediadata.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.ipbmediadata.models.SortData
import com.progressterra.ipbandroidapi.api.ipbmediadata.models.TypeComparison
import com.progressterra.ipbandroidapi.api.ipbmediadata.models.TypeVariantSort
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources

interface FetchAvatarUseCase {

    suspend operator fun invoke(): Result<String>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val ipbMediaDataService: IPBMediaDataService,
        makeToastUseCase: MakeToastUseCase, manageResources: ManageResources
    ) : FetchAvatarUseCase, AbstractTokenUseCase(
        obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke(): Result<String> = withToken {
            ipbMediaDataService.attachedToClient(
                accessToken = it, filterAndSort = FilterAndSort(
                    listFields = listOf(
                        FieldForFilter(
                            fieldName = "alias",
                            listValue = listOf("profilePicture"),
                            comparison = TypeComparison.EQUALS_STRONG
                        )
                    ),
                    sort = SortData(
                        fieldName = "dateAdded",
                        variantSort = TypeVariantSort.DESC
                    ),
                    searchData = "",
                    skip = 0,
                    take = 1
                )
            ).dataList?.firstOrNull()?.urlData ?: ""
        }
    }
}