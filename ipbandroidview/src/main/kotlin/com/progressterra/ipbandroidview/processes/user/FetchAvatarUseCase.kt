package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidapi.api.ipbmediadata.IPBMediaDataRepository
import com.progressterra.ipbandroidapi.api.ipbmediadata.model.FieldForFilter
import com.progressterra.ipbandroidapi.api.ipbmediadata.model.FilterAndSort
import com.progressterra.ipbandroidapi.api.ipbmediadata.model.SortData
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface FetchAvatarUseCase {

    suspend operator fun invoke(): Result<String>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val ipbMediaDataRepository: IPBMediaDataRepository,
        makeToastUseCase: MakeToastUseCase, manageResources: ManageResources
    ) : FetchAvatarUseCase, AbstractTokenUseCase(
        obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke(): Result<String> = withToken {
            ipbMediaDataRepository.attachedToClient(
                accessToken = it, filterAndSort = FilterAndSort(
                    listFields = listOf(
                        FieldForFilter(
                            fieldName = "alias",
                            listValue = listOf("profilePicture"),
                            comparison = "equalsStrong"
                        )
                    ),
                    sort = SortData(
                        fieldName = "dateAdded",
                        variantSort = "desc"
                    ),
                    searchData = "",
                    skip = 0,
                    take = 1
                )
            ).getOrThrow()?.firstOrNull()?.urlData ?: ""
        }
    }
}