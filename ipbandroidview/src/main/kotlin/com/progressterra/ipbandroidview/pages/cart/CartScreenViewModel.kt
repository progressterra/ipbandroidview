package com.progressterra.ipbandroidview.pages.cart

import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.cartcard.CartCardEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.FetchCartUseCase
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class CartScreenViewModel(
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val fetchCartUseCase: FetchCartUseCase
) : AbstractNonInputViewModel<CartScreenState, CartScreenEffect>(), UseCartScreen {

    init {
        onBackground {
            fetchCartUseCase.resultFlow.collect { result ->
                val call = result.onSuccess { newState -> emitState { newState } }
                emitState {
                    it.copy(
                        screen = it.screen.copy(state = call.isSuccess.toScreenState()),
                        summary = it.summary.copy(proceed = it.summary.proceed.copy(enabled = it.items.items.isNotEmpty()))
                    )
                }
            }
        }
    }

    override fun createInitialState() = CartScreenState()

    override fun refresh() {
        onBackground { fetchCartUseCase() }
    }

    override fun handle(event: CartCardEvent) {
        onBackground {
            when (event) {
                is CartCardEvent.Open -> postEffect(CartScreenEffect.OnItem(event.id))
                is CartCardEvent.RemoveFromCart -> removeFromCartUseCase(event.id).onSuccess { newState ->
                    emitState { newState.copy(screen = it.screen.copy(state = ScreenState.SUCCESS)) }
                }.onFailure {
                    emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
                }
            }
        }
    }

    override fun handle(event: TopBarEvent) = Unit

    override fun handle(event: ButtonEvent) {
        postEffect(CartScreenEffect.Payment)
    }

    override fun handle(event: CounterEvent) {
        onBackground {
            emitState { createInitialState() }
            when (event) {
                is CounterEvent.Add -> addToCartUseCase(event.id).onSuccess { newState ->
                    emitState { newState.copy(screen = it.screen.copy(state = ScreenState.SUCCESS)) }
                }.onFailure {
                    emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
                }

                is CounterEvent.Remove -> removeFromCartUseCase(event.id).onSuccess { newState ->
                    emitState { newState.copy(screen = it.screen.copy(state = ScreenState.SUCCESS)) }
                }.onFailure {
                    emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
                }
            }
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}