package com.progressterra.ipbandroidview.processes.payments

import com.progressterra.ipbandroidapi.api.paymentdata.PaymentDataRepository
import com.progressterra.ipbandroidapi.api.paymentdata.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.paymentdata.models.SortData
import com.progressterra.ipbandroidapi.api.paymentdata.models.TypeVariantSort
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface FetchMainCardIdUseCase {

    suspend operator fun invoke(): Result<String>

    class Base(
        private val paymentDataRepository: PaymentDataRepository,
        obtainAccessToken: ObtainAccessToken,
    ) : FetchMainCardIdUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<String> = withToken {
            paymentDataRepository.clientAreaList(
                accessToken = it,
                body = FilterAndSort(
                    listFields = emptyList(),
                    sort = SortData(
                        fieldName = "dateAdded",
                        variantSort = TypeVariantSort.DESC
                    ),
                    searchData = "",
                    skip = 0,
                    take = 1
                )
            ).getOrThrow()?.firstOrNull()?.idUnique ?: ""
        }
    }
}