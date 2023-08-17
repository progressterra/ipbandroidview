package com.progressterra.ipbandroidview.pages.withdrawal

import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.payments.FetchWithdrawalUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent

class WithdrawalScreenViewModel(
    private val fetchWithdrawalTransactionsUseCase: FetchWithdrawalTransactionsUseCase,
    private val fetchWithdrawalUseCase: FetchWithdrawalUseCase
) : BaseViewModel<WithdrawalScreenState, WithdrawalScreenEvent>(),
    UseWithdrawalScreen {

    override fun createInitialState(): WithdrawalScreenState = WithdrawalScreenState()

    fun refresh() {
        onBackground {
            emitState { it.copy(screen = ScreenState.LOADING) }
            var isSuccess = true
            fetchWithdrawalTransactionsUseCase().onSuccess { transactions ->
                emitState {
                    it.copy(transactions = cachePaging(transactions))
                }
            }.onFailure { isSuccess = false }
            fetchWithdrawalUseCase().onSuccess { canBeWithdrawal ->
                emitState { it.copy(canBeWithdrawal = canBeWithdrawal) }
            }.onFailure { isSuccess = false }
            emitState { it.copy(screen = isSuccess.toScreenState()) }
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(WithdrawalScreenEvent.Back)
    }

    override fun handle(event: ButtonEvent) {
        postEffect(WithdrawalScreenEvent.New)
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }
}