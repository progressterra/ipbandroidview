package com.progressterra.ipbandroidview.pages.cart

import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.cartcard.CartCardEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent

class CartViewModel(
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val cartUseCase: CartUseCase
) : BaseViewModel<CartState, CartEvent>(), UseCartScreen {

    override fun createInitialState() = CartState()

    fun refresh() {
        onBackground {
            val call = cartUseCase().onSuccess { this.emitState { it } }
            this.emitState { it.copy(screenState = call.isSuccess.toScreenState()) }
        }
    }

    override fun handle(event: CartCardEvent) {
        onBackground {
            when (event) {
                is CartCardEvent.Open -> postEffect(CartEvent.OnItem(event.id))
                is CartCardEvent.RemoveFromCart -> removeFromCartUseCase(event.id).onSuccess {
                    this.emitState { it.copy(screenState = ScreenState.SUCCESS) }
                }.onFailure {
                    this.emitState { it.copy(screenState = ScreenState.ERROR) }
                }
            }
        }
    }

    override fun handle(event: TopBarEvent) = Unit

    override fun handle(event: ButtonEvent) {
        postEffect(CartEvent.Payment)
    }

    override fun handle(event: CounterEvent) {
        onBackground {
            when (event) {
                is CounterEvent.Add -> addToCartUseCase(event.id).onSuccess { newState ->
                    this.emitState { newState.copy(screenState = ScreenState.SUCCESS) }
                }.onFailure {
                    this.emitState { it.copy(screenState = ScreenState.ERROR) }
                }

                is CounterEvent.Remove -> removeFromCartUseCase(event.id).onSuccess { newState ->
                    this.emitState { newState.copy(screenState = ScreenState.SUCCESS) }
                }.onFailure {
                    this.emitState { it.copy(screenState = ScreenState.ERROR) }
                }
            }
        }
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }
}