package com.progressterra.ipbandroidview.pages.bankcards

import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.documents.models.FieldForFilter
import com.progressterra.ipbandroidapi.api.documents.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.documents.models.SortData
import com.progressterra.ipbandroidapi.api.documents.models.TypeComparison
import com.progressterra.ipbandroidapi.api.documents.models.TypeVariantSort
import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface FetchMainCardIdUseCase {

    suspend operator fun invoke(): Result<String>

    class Base(
        private val documentsRepository: DocumentsRepository,
        obtainAccessToken: ObtainAccessToken,
    ) : FetchMainCardIdUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<String> = withToken {
            documentsRepository.docs(
                accessToken = it,
                filter = FilterAndSort(
                    listFields = listOf(
                        FieldForFilter(
                            fieldName = "idrfCharacteristicType",
                            listValue = listOf(IpbAndroidViewSettings.BANK_CARDS_TYPE_ID),
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
            ).getOrThrow()?.firstOrNull()?.idUnique ?: ""
        }
    }
}