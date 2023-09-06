package com.progressterra.ipbandroidview.features.bonuses

import com.progressterra.ipbandroidapi.api.balance.BalanceRepository
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface BonusesUseCase {

    suspend operator fun invoke(): Result<BonusesState>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val balanceRepository: BalanceRepository
    ) : BonusesUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<BonusesState> = withToken { token ->
            BonusesState(
                bonuses = "0",
                roubles = balanceRepository.client(token).getOrThrow()?.amount?.toInt()?.toString()
                    ?: ""
            )
        }
    }
}