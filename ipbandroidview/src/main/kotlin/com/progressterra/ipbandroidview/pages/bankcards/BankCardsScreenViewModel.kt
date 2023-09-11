package com.progressterra.ipbandroidview.pages.bankcards

import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.bankcard.BankCardEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.payments.FetchConfirmedBankCardsUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class BankCardsScreenViewModel(
    private val fetchUnconfirmedBankCardsUseCase: FetchUnconfirmedBankCardsUseCase,
    private val fetchConfirmedBankCardsUseCase: FetchConfirmedBankCardsUseCase
) : AbstractNonInputViewModel<BankCardsScreenState, BankCardsScreenEffect>(),
    UseBankCardsScreen {

    override fun createInitialState(): BankCardsScreenState = BankCardsScreenState()

    override fun refresh() {
        onBackground {
            emitState { createInitialState() }
            var isSuccess = true
            fetchUnconfirmedBankCardsUseCase().onSuccess { cards ->
                emitState {
                    it.copy(otherCards = cachePaging(cards))
                }
            }.onFailure {
                isSuccess = false
            }
            fetchConfirmedBankCardsUseCase().onSuccess { cards ->
                emitState {
                    it.copy(addedCards = cachePaging(cards))
                }
            }.onFailure {
                isSuccess = false
            }
            emitState { it.copy(screen = it.screen.copy(state = isSuccess.toScreenState())) }
        }
    }

    override fun handleEvent(event: BankCardEvent) {
        when (event) {
            is BankCardEvent.Click -> postEffect(BankCardsScreenEffect.OpenDetails(event.state.document))

            is BankCardEvent.Delete -> Unit
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(BankCardsScreenEffect.Back)
    }

    override fun handle(event: ButtonEvent) {
        postEffect(BankCardsScreenEffect.OpenDetails(Document()))
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}