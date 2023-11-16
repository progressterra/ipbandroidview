package com.progressterra.ipbandroidview.pages.newwithdrawal

import androidx.paging.map
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.bankcard.BankCardEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.withdrawal.CreateNewWithdrawalUseCase
import com.progressterra.ipbandroidview.processes.bankcards.FetchConfirmedBankCardsUseCase
import com.progressterra.ipbandroidview.processes.withdrawal.FetchWithdrawalUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.replaceById
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import kotlinx.coroutines.flow.map

class NewWithdrawalScreenViewModel(
    private val fetchWithdrawalUseCase: FetchWithdrawalUseCase,
    private val fetchConfirmedBankCardsUseCase: FetchConfirmedBankCardsUseCase,
    private val newWithdrawalUseCase: CreateNewWithdrawalUseCase
) : AbstractNonInputViewModel<NewWithdrawalScreenState, NewWithdrawalScreenEffect>(),
    UseNewWithdrawalScreen {

    override fun createInitialState(): NewWithdrawalScreenState = NewWithdrawalScreenState()

    override fun refresh() {
        onBackground {
            emitState { createInitialState() }
            var isSuccess = true
            fetchConfirmedBankCardsUseCase().onSuccess { cards ->
                emitState {
                    it.copy(cardsFlow = cachePaging(cards))
                }
            }.onFailure { isSuccess = false }
            fetchWithdrawalUseCase().onSuccess { canBeWithdrawal ->
                emitState { it.copy(canBeWithdrawal = canBeWithdrawal) }
            }.onFailure { isSuccess = false }
            emitState { it.copy(screen = it.screen.copy(state = isSuccess.toScreenState())) }
        }
    }

    override fun handle(event: NewWithdrawalScreenEvent) {
        emitState {
            it.copy(cards = event.items)
        }
    }

    override fun handle(event: TextFieldEvent) {
        when (event) {
            is TextFieldEvent.Action -> Unit
            is TextFieldEvent.AdditionalAction -> Unit
            is TextFieldEvent.TextChanged -> emitState { it.copy(input = it.input.copy(text = event.text)) }
        }
        validate()
    }

    override fun handleEvent(event: BankCardEvent) {
        if (event.state != currentState.cards.firstOrNull { it.isSelected }) {
            emitState {
                val newCard = event.state.copy(isSelected = true)
                it.copy(cardsFlow = it.cardsFlow.map { pd ->
                    pd.map { c -> c.copy(isSelected = false) }.replaceById(newCard)
                }, cards = it.cards.map { c -> c.copy(isSelected = false) }.replaceById(newCard))
            }
            validate()
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(NewWithdrawalScreenEffect.Back)
    }

    override fun handle(event: ButtonEvent) {
        when (event.id) {
            "add" -> onBackground {
                emitState { it.copy(add = it.add.copy(enabled = false)) }
                newWithdrawalUseCase(
                    currentState.cards.first { it.isSelected },
                    currentState.input.formatByType()
                ).onSuccess {
                    postEffect(NewWithdrawalScreenEffect.Back)
                    postEffect(NewWithdrawalScreenEffect.Toast(R.string.success))
                }
                emitState { it.copy(add = it.add.copy(enabled = true)) }
            }

            "all" -> emitState { it.copy(input = it.input.copy(text = currentState.canBeWithdrawal.toStringRaw())) }
        }
    }

    private fun validate() {
        emitState {
            it.copy(
                add = it.add.copy(
                    enabled = currentState.input.formatByType()
                        .isNotEmpty() && currentState.input.formatByType() != "0" && currentState.cards.any { card -> card.isSelected })
            )
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}