package com.progressterra.ipbandroidview.features.bonuses

import com.progressterra.ipbandroidapi.api.balance.BalanceRepository
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.payments.HasCardsUseCase
import com.progressterra.ipbandroidview.shared.AbstractCacheTokenUseCase
import com.progressterra.ipbandroidview.shared.CacheUseCase
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState

interface FetchBonusesUseCase : CacheUseCase<BonusesState> {

    suspend operator fun invoke()

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val balanceRepository: BalanceRepository,
        private val hasCardsUseCase: HasCardsUseCase
    ) : FetchBonusesUseCase, AbstractCacheTokenUseCase<BonusesState>(obtainAccessToken) {

        override suspend fun invoke() {
            withCache { token ->
                val hasCards = hasCardsUseCase().getOrThrow()
                BonusesState(
                    roubles = balanceRepository.client(token).getOrThrow()?.amount?.toInt()
                        ?.toString()
                        ?: "",
                    hasCards = hasCards,
                    state = StateColumnState(id = "bonuses", state = ScreenState.SUCCESS)
                )
            }
        }
    }
}