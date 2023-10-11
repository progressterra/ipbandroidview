package com.progressterra.ipbandroidview.pages.bonusesdetails

import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.bonuses.BonusesEvent
import com.progressterra.ipbandroidview.features.bonuses.BonusesModule
import com.progressterra.ipbandroidview.features.bonuses.BonusesModuleUser
import com.progressterra.ipbandroidview.features.bonuses.BonusesState
import com.progressterra.ipbandroidview.features.bonuses.FetchBonusesUseCase
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.widgets.bonusestransactions.FetchBonusesTransactionsUseCase

class BonusesDetailsScreenViewModel(
    private val fetchBonusesUseCase: FetchBonusesUseCase,
    private val fetchBonusesTransactionsUseCase: FetchBonusesTransactionsUseCase
) : AbstractNonInputViewModel<BonusesDetailsScreenState, BonusesDetailsScreenEffect>(),
    UseBonusesDetailsScreen {

    override fun createInitialState() = BonusesDetailsScreenState()

    override fun refresh() {
        onBackground {
            emitState { createInitialState() }
            var isSuccess = true
            bonusesModule.refresh()
            fetchBonusesTransactionsUseCase().onSuccess { transactions ->
                emitState {
                    it.copy(transactions = transactions)
                }
            }.onFailure {
                isSuccess = false
            }
            emitState {
                it.copy(screen = it.screen.copy(state = isSuccess.toScreenState()))
            }
        }
    }

    private val bonusesModule = BonusesModule(
        fetchBonusesUseCase = fetchBonusesUseCase,
        operations = this,
        user = object : BonusesModuleUser {

            override fun onBonusesTransactions() = Unit

            override fun onWithdrawal() = Unit

            override fun onAddCard() = Unit

            override fun emitModuleState(reducer: (BonusesState) -> BonusesState) {
                emitState { it.copy(bonuses = reducer(currentState.bonuses)) }
            }

            override val moduleState: BonusesState
                get() = currentState.bonuses
        }
    )

    override fun handle(event: BonusesEvent) {
        bonusesModule.handle(event)
    }

    override fun handle(event: TopBarEvent) {
        postEffect(BonusesDetailsScreenEffect.Back)
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}