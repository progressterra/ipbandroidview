package com.progressterra.ipbandroidview.pages.cart

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.cartcard.CartCardEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CartViewModel(
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val cartUseCase: CartUseCase
) : ViewModel(), ContainerHost<CartState, CartEvent>, UseCartScreen {

    override val container = container<CartState, CartEvent>(CartState())

    fun refresh() {
        intent {
            var isSuccess = true
            cartUseCase()
                .onSuccess { reduce { it } }
                .onFailure { isSuccess = false }
            reduce { CartState.screenState.set(state, isSuccess.toScreenState()) }
        }
    }

    override fun handle(event: CartCardEvent) {
        intent {
            when (event) {
                is CartCardEvent.Open -> postSideEffect(CartEvent.OnItem(event.id))
                is CartCardEvent.RemoveFromCart -> removeFromCartUseCase(event.id).onSuccess {
                    reduce { CartState.screenState.set(state, ScreenState.SUCCESS) }
                }.onFailure {
                    reduce { CartState.screenState.set(state, ScreenState.ERROR) }
                }
            }
        }
    }

    override fun handle(event: TopBarEvent) = Unit

    override fun handle(event: ButtonEvent) {
        intent { postSideEffect(CartEvent.Payment) }
    }

    override fun handle(event: CounterEvent) {
        intent {
            when (event) {
                is CounterEvent.Add -> addToCartUseCase(event.id).onSuccess {
                    reduce { it }
                    reduce { CartState.screenState.set(state, ScreenState.SUCCESS) }
                }.onFailure {
                    reduce { CartState.screenState.set(state, ScreenState.ERROR) }
                }

                is CounterEvent.Remove -> removeFromCartUseCase(event.id).onSuccess {
                    reduce { it }
                    reduce { CartState.screenState.set(state, ScreenState.SUCCESS) }
                }.onFailure {
                    reduce { CartState.screenState.set(state, ScreenState.ERROR) }
                }
            }
        }
    }

    override fun handle(event: StateBoxEvent) {
        intent { refresh() }
    }
}