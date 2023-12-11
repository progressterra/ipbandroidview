package com.progressterra.ipbandroidview.features.bonuses

import com.progressterra.ipbandroidview.processes.bonuses.FetchBonusesUseCase
import com.progressterra.ipbandroidview.shared.mvi.Operations
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import kotlinx.coroutines.flow.collectLatest

class BonusesModule(
    private val fetchBonusesUseCase: FetchBonusesUseCase,
    operations: Operations,
    user: BonusesModuleUser
) : BonusesModuleUser by user, Operations by operations, UseBonuses {

    init {
        onBackground {
            fetchBonusesUseCase.resultFlow.collectLatest { result ->
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
            emitModuleState { it.copy(state = it.state.copy(state = ScreenState.LOADING)) }
            fetchBonusesUseCase()
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }

    override fun handle(event: ButtonEvent) {
        if (event.id == "auth") {
            onAuth()
        }
    }

    override fun handle(event: BonusesEvent) {
        when (event) {
            is BonusesEvent.Transactions -> onBonusesTransactions()
            is BonusesEvent.Withdrawal -> onWithdrawal()
            is BonusesEvent.AddCard -> onAddCard()
        }
    }
}

