package com.progressterra.ipbandroidview.processes.withdrawal

import com.progressterra.ipbandroidapi.api.payment.PaymentRepository
import com.progressterra.ipbandroidapi.api.payment.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.payment.models.SortData
import com.progressterra.ipbandroidapi.api.payment.models.TypeVariantSort
import com.progressterra.ipbandroidview.entities.toWithdrawalTransactionState
import com.progressterra.ipbandroidview.features.withdrawaltransaction.WithdrawalTransactionState
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractSource

class WithdrawalTransactionsCardsSource(
    private val paymentRepository: PaymentRepository,
    private val obtainAccessToken: ObtainAccessToken
) : AbstractSource<Nothing, WithdrawalTransactionState>() {

    override val pageSize = 10

    override suspend fun loadPage(skip: Int, take: Int): Result<List<WithdrawalTransactionState>> =
        runCatching {
            val token = obtainAccessToken().getOrThrow()
            paymentRepository.clientAreaPaymentList(
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
            ).getOrThrow()?.map {
                it.toWithdrawalTransactionState()
            } ?: emptyList()
        }
}