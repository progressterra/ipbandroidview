package com.progressterra.ipbandroidview.processes.interests

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.iamhere.models.SortData
import com.progressterra.ipbandroidapi.api.iamhere.models.TypeVariantSort
import com.progressterra.ipbandroidview.entities.Interest
import com.progressterra.ipbandroidview.entities.toInterest
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.PagingUseCase

interface FetchInterestsUseCase {

    suspend operator fun invoke(): Result<List<Interest>>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val service: ImhService
    ) : FetchInterestsUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<List<Interest>> = withToken { token ->
            service.clientInterestList(
                token = token,
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
            ).dataList?.map { it.toInterest() } ?: emptyList()
        }
    }
}
