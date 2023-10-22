package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidapi.api.payment.PaymentRepository
import com.progressterra.ipbandroidview.features.withdrawaltransaction.WithdrawalTransactionState
import com.progressterra.ipbandroidview.shared.PagingUseCase

interface FetchWithdrawalTransactionsUseCase : PagingUseCase<Nothing, WithdrawalTransactionState> {

    class Base(
        private val paymentRepository: PaymentRepository,
        private val obtainAccessToken: ObtainAccessToken
    ) : PagingUseCase.Abstract<Nothing, WithdrawalTransactionState>(),
        FetchWithdrawalTransactionsUseCase {

        override fun createSource() =
            WithdrawalTransactionsCardsSource(
                paymentRepository,
                obtainAccessToken
            )
    }
}

