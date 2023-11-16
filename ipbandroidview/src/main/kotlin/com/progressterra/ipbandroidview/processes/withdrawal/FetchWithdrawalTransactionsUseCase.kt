package com.progressterra.ipbandroidview.processes.withdrawal

import com.progressterra.ipbandroidapi.api.payment.PaymentRepository
import com.progressterra.ipbandroidview.features.withdrawaltransaction.WithdrawalTransactionState
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.PagingUseCase

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

