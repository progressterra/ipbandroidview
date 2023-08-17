package com.progressterra.ipbandroidview.pages.bankcards

import com.google.gson.Gson
import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.documents.models.FieldForFilter
import com.progressterra.ipbandroidapi.api.documents.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.documents.models.SortData
import com.progressterra.ipbandroidapi.api.documents.models.TypeComparison
import com.progressterra.ipbandroidapi.api.documents.models.TypeVariantSort
import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.entities.toDocument
import com.progressterra.ipbandroidview.features.bankcard.BankCardState
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.payments.FetchMainCardIdUseCase
import com.progressterra.ipbandroidview.shared.AbstractSource
import com.progressterra.ipbandroidview.shared.CreateId

class UnconfirmedBankCardsSource(
    private val documentsRepository: DocumentsRepository,
    private val obtainAccessToken: ObtainAccessToken,
    private val gson: Gson,
    private val createId: CreateId,
    private val fetchMainCardIdUseCase: FetchMainCardIdUseCase
) : AbstractSource<Nothing, BankCardState>() {

    override val pageSize = 15

    override suspend fun loadPage(skip: Int, take: Int): Result<List<BankCardState>> = runCatching {
        val token = obtainAccessToken().getOrThrow()
        val mainCardId = fetchMainCardIdUseCase().getOrThrow()
        documentsRepository.docs(
            accessToken = token,
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
                skip = skip,
                take = take
            )
        ).getOrThrow()?.map {
            it.toDocument(gson, createId).toBankCardState().copy(
                isMainCard = mainCardId == it.idUnique
            )
        } ?: emptyList()
    }
}