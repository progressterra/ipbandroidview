package com.progressterra.ipbandroidview.processes.occupacion

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.FieldForFilter
import com.progressterra.ipbandroidapi.api.iamhere.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.iamhere.models.SortData
import com.progressterra.ipbandroidapi.api.iamhere.models.TypeComparison
import com.progressterra.ipbandroidapi.api.iamhere.models.TypeVariantSort
import com.progressterra.ipbandroidview.entities.Interest
import com.progressterra.ipbandroidview.entities.toInterest
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface FetchOccupationsUseCase {

    suspend operator fun invoke(): Result<List<Interest>>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val service: ImhService
    ) : FetchOccupationsUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<List<Interest>> = withToken { token ->
            service.interestList(
                token = token,
                body = FilterAndSort(
                    listFields = listOf(
                        FieldForFilter(
                            fieldName = "interestType",
                            listValue = listOf("profession"),
                            comparison = TypeComparison.EQUALS_STRONG
                        )
                    ),
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
