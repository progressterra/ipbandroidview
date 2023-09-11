package com.progressterra.ipbandroidview.pages.withdrawal

import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.payments.FetchWithdrawalUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class WithdrawalScreenViewModel(
    private val fetchWithdrawalTransactionsUseCase: FetchWithdrawalTransactionsUseCase,
    private val fetchWithdrawalUseCase: FetchWithdrawalUseCase
) : AbstractNonInputViewModel<WithdrawalScreenState, WithdrawalScreenEffect>(),
    UseWithdrawalScreen {

    override fun createInitialState(): WithdrawalScreenState = WithdrawalScreenState()

    override fun refresh() {
        onBackground {
            emitState { createInitialState() }
            var isSuccess = true
            fetchWithdrawalTransactionsUseCase().onSuccess { transactions ->
                emitState {
                    it.copy(transactions = cachePaging(transactions))
                }
            }.onFailure { isSuccess = false }
            fetchWithdrawalUseCase().onSuccess { canBeWithdrawal ->
                emitState { it.copy(canBeWithdrawal = canBeWithdrawal) }
            }.onFailure { isSuccess = false }
            emitState { it.copy(screen = it.screen.copy(state = isSuccess.toScreenState())) }
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(WithdrawalScreenEffect.Back)
    }

    override fun handle(event: ButtonEvent) {
        postEffect(WithdrawalScreenEffect.New)
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}