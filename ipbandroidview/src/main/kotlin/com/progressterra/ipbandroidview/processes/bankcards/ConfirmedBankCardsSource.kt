package com.progressterra.ipbandroidview.processes.bankcards

import com.progressterra.ipbandroidapi.api.paymentdata.PaymentDataRepository
import com.progressterra.ipbandroidapi.api.paymentdata.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.paymentdata.models.SortData
import com.progressterra.ipbandroidapi.api.paymentdata.models.TypeVariantSort
import com.progressterra.ipbandroidview.entities.toBankCardState
import com.progressterra.ipbandroidview.features.bankcard.BankCardState
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractSource

class ConfirmedBankCardsSource(
    private val paymentDataRepository: PaymentDataRepository,
    private val obtainAccessToken: ObtainAccessToken,
    private val fetchMainCardIdUseCase: FetchMainCardIdUseCase
) : AbstractSource<Nothing, BankCardState>() {

    override val pageSize = 15

    override suspend fun loadPage(skip: Int, take: Int): Result<Pair<Int, List<BankCardState>>> =
        runCatching {
            val token = obtainAccessToken().getOrThrow()
            val mainCardId = fetchMainCardIdUseCase().getOrThrow()
            val response = paymentDataRepository.clientAreaList(
                accessToken = token,
                body = FilterAndSort(
                    listFields = emptyList(),
                    sort = SortData(
                        fieldName = "dateAdded",
                        variantSort = TypeVariantSort.DESC
                    ),
                    searchData = "",
                    skip = skip,
                    take = take
                )
            ).getOrThrow() ?: emptyList()
            response.size to response.map {
                it.toBankCardState().copy(
                    isMainCard = mainCardId == it.idUnique,
                    isSelected = mainCardId == it.idUnique
                )
            }
        }
}