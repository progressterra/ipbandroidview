package com.progressterra.ipbandroidview.processes.bankcards

import com.progressterra.ipbandroidapi.api.paymentdata.PaymentDataRepository
import com.progressterra.ipbandroidview.features.bankcard.BankCardState
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.PagingUseCase

interface FetchConfirmedBankCardsUseCase : PagingUseCase<Nothing, BankCardState> {

    class Base(
        private val paymentDataRepository: PaymentDataRepository,
        private val obtainAccessToken: ObtainAccessToken,
        private val fetchMainCardIdUseCase: FetchMainCardIdUseCase
    ) : PagingUseCase.Abstract<Nothing, BankCardState>(), FetchConfirmedBankCardsUseCase {

        override fun createSource() =
            ConfirmedBankCardsSource(
                paymentDataRepository,
                obtainAccessToken,
                fetchMainCardIdUseCase
            )
    }
}

