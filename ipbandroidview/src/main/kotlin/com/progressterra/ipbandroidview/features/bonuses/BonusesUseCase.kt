package com.progressterra.ipbandroidview.features.bonuses

import com.progressterra.ipbandroidapi.api.balance.BalanceRepository
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.payments.HasCardsUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface BonusesUseCase {

    suspend operator fun invoke(): Result<BonusesState>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val balanceRepository: BalanceRepository,
        private val hasCardsUseCase: HasCardsUseCase
    ) : BonusesUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<BonusesState> = withToken { token ->
            val hasCards = hasCardsUseCase().getOrThrow()
            BonusesState(
                roubles = balanceRepository.client(token).getOrThrow()?.amount?.toInt()?.toString()
                    ?: "",
                hasCards = hasCards
            )
        }
    }
}