package com.progressterra.ipbandroidview.pages.newwithdrawal

import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.bankcard.BankCardEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.payments.FetchConfirmedBankCardsUseCase
import com.progressterra.ipbandroidview.processes.payments.FetchWithdrawalUseCase
import com.progressterra.ipbandroidview.shared.mvi.BaseViewModel
import com.progressterra.ipbandroidview.shared.replaceById
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import kotlinx.coroutines.flow.map

class NewWithdrawalScreenViewModel(
    private val fetchWithdrawalUseCase: FetchWithdrawalUseCase,
    private val fetchConfirmedBankCardsUseCase: FetchConfirmedBankCardsUseCase,
    private val newWithdrawalUseCase: CreateNewWithdrawalUseCase
) : BaseViewModel<NewWithdrawalScreenState, NewWithdrawalScreenEvent>(),
    UseNewWithdrawalScreen {

    override fun createInitialState(): NewWithdrawalScreenState = NewWithdrawalScreenState(
        add = ButtonState(
            id = "add",
            enabled = false
        )
    )

    fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            var isSuccess = true
            fetchConfirmedBankCardsUseCase().onSuccess { cards ->
                emitState {
                    it.copy(cards = cachePaging(cards))
                }
            }.onFailure { isSuccess = false }
            fetchWithdrawalUseCase().onSuccess { canBeWithdrawal ->
                emitState { it.copy(canBeWithdrawal = canBeWithdrawal) }
            }.onFailure { isSuccess = false }
            emitState { it.copy(screen = it.screen.copy(state = isSuccess.toScreenState())) }
        }
    }

    override fun handle(event: TextFieldEvent) {
        if (event.id == "input") {
            when (event) {
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
                is TextFieldEvent.TextChanged -> emitState { it.copy(input = it.input.copy(text = event.text)) }
            }
        }
    }

    override fun handleEvent(event: BankCardEvent) {
        emitState {
            val newCard = event.state.copy(isSelected = true)
            it.copy(cards = it.cards.map { pd ->
                pd.replaceById(newCard)
            }, selectedCard = newCard, add = it.add.copy(enabled = true))
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(NewWithdrawalScreenEvent.Back)
    }

    override fun handle(event: ButtonEvent) {
        when (event.id) {
            "add" -> onBackground {
                newWithdrawalUseCase(currentState.selectedCard, currentState.input.text).onSuccess {
                    postEffect(NewWithdrawalScreenEvent.Back)
                    postEffect(NewWithdrawalScreenEvent.Toast(R.string.success))
                }
            }

            "all" -> emitState { it.copy(input = it.input.copy(text = currentState.canBeWithdrawal.toStringRaw())) }
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}