package com.progressterra.ipbandroidview.processes.bonuses

import com.progressterra.ipbandroidapi.api.balance.BalanceRepository
import com.progressterra.ipbandroidview.features.bonuses.BonusesState
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.bankcards.HasCardsUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractCacheTokenUseCase
import com.progressterra.ipbandroidview.shared.mvi.CacheUseCase
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState

interface FetchBonusesUseCase : CacheUseCase<BonusesState> {

    suspend operator fun invoke()

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val balanceRepository: BalanceRepository,
        private val hasCardsUseCase: HasCardsUseCase, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : FetchBonusesUseCase, AbstractCacheTokenUseCase<BonusesState>(obtainAccessToken,
        makeToastUseCase, manageResources
    ) {

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