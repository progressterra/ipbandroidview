package com.progressterra.ipbandroidview.processes.payments

import com.progressterra.ipbandroidview.pages.bankcards.FetchUnconfirmedBankCardsUseCase
import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase
import kotlinx.coroutines.flow.count

interface HasCardsUseCase {

    suspend operator fun invoke(): Result<Boolean>

    class Base(
        private val fetchUnconfirmedBankCardsUseCase: FetchUnconfirmedBankCardsUseCase,
        private val fetchConfirmedBankCardsUseCase: FetchConfirmedBankCardsUseCase
    ) : HasCardsUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(): Result<Boolean> = handle {
            fetchConfirmedBankCardsUseCase().getOrThrow().count() != 0 || fetchUnconfirmedBankCardsUseCase().getOrThrow().count() != 0
        }
    }
}