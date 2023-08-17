package com.progressterra.ipbandroidview.pages.bankcards

import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.bankcard.BankCardEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.pages.bankcarddetails.BankCardDetailsScreenState
import com.progressterra.ipbandroidview.processes.payments.FetchConfirmedBankCardsUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent

class BankCardsScreenViewModel(
    private val fetchUnconfirmedBankCardsUseCase: FetchUnconfirmedBankCardsUseCase,
    private val fetchConfirmedBankCardsUseCase: FetchConfirmedBankCardsUseCase
) : BaseViewModel<BankCardsScreenState, BankCardsScreenEvent>(),
    UseBankCardsScreen {

    override fun createInitialState(): BankCardsScreenState = BankCardsScreenState()

    fun refresh() {
        onBackground {
            emitState { it.copy(screen = ScreenState.LOADING) }
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
            emitState { it.copy(screen = isSuccess.toScreenState()) }
        }
    }

    override fun handleEvent(event: BankCardEvent) {
        when (event) {
            is BankCardEvent.Click -> postEffect(BankCardsScreenEvent.OpenDetails(event.state.toBankCardDetailsScreenState()))

            is BankCardEvent.Delete -> Unit
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(BankCardsScreenEvent.Back)
    }

    override fun handle(event: ButtonEvent) {
        postEffect(BankCardsScreenEvent.OpenDetails(BankCardDetailsScreenState()))
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }
}