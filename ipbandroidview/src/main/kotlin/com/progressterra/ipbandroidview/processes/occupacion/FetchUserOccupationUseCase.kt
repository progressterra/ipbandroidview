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

interface FetchUserOccupationUseCase {

    suspend operator fun invoke(): Result<Interest?>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val service: ImhService
    ) : FetchUserOccupationUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<Interest?> = withToken { token ->
            service.clientInterestList(
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
                    take = 1
                )
            ).dataList?.firstOrNull()?.toInterest()
        }
    }
}