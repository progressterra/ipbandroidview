package com.progressterra.ipbandroidview.features.bonuses

import com.progressterra.ipbandroidview.shared.mvi.Operations
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class BonusesModule(
    private val fetchBonusesUseCase: FetchBonusesUseCase,
    operations: Operations,
    user: BonusesModuleUser
) : BonusesModuleUser by user, Operations by operations, UseBonuses {

    init {
        onBackground {
            fetchBonusesUseCase.resultFlow.collect { result ->
                result.onSuccess { bonuses ->
                    emitModuleState { bonuses }
                }.onFailure {
                    emitModuleState { it.copy(state = it.state.copy(state = ScreenState.ERROR)) }
                }
            }
        }
    }

    fun refresh() {
        onBackground {
            fetchBonusesUseCase()
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }

    override fun handle(event: BonusesEvent) {
        when (event) {
            is BonusesEvent.Transactions -> onBonusesTransactions()
            is BonusesEvent.Withdrawal -> onWithdrawal()
            is BonusesEvent.AddCard -> onAddCard()
        }
    }
}

