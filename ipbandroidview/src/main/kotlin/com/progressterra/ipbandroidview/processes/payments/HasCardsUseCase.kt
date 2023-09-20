package com.progressterra.ipbandroidview.processes.payments

import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.documents.models.FieldForFilter
import com.progressterra.ipbandroidapi.api.documents.models.TypeComparison
import com.progressterra.ipbandroidapi.api.paymentdata.PaymentDataRepository
import com.progressterra.ipbandroidapi.api.paymentdata.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.paymentdata.models.SortData
import com.progressterra.ipbandroidapi.api.paymentdata.models.TypeVariantSort
import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface HasCardsUseCase {

    suspend operator fun invoke(): Result<Boolean>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val paymentDataRepository: PaymentDataRepository,
        private val documentsRepository: DocumentsRepository
    ) : HasCardsUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<Boolean> = withToken { token ->
            val hasConfirmed = paymentDataRepository.clientAreaList(
                accessToken = token,
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
            ).getOrThrow()?.isNotEmpty() ?: false
            val hasUnconfirmed = documentsRepository.docs(
                accessToken = token,
                filter = com.progressterra.ipbandroidapi.api.documents.models.FilterAndSort(
                    listFields = listOf(
                        FieldForFilter(
                            fieldName = "idrfCharacteristicType",
                            listValue = listOf(IpbAndroidViewSettings.BANK_CARDS_TYPE_ID),
                            comparison = TypeComparison.EQUALS_STRONG
                        )
                    ),
                    sort = com.progressterra.ipbandroidapi.api.documents.models.SortData(
                        fieldName = "dateAdded",
                        variantSort = com.progressterra.ipbandroidapi.api.documents.models.TypeVariantSort.DESC
                    ),
                    searchData = "",
                    skip = 0,
                    take = 1
                )
            ).getOrThrow()?.isNotEmpty() ?: false
            hasConfirmed || hasUnconfirmed
        }
    }
}