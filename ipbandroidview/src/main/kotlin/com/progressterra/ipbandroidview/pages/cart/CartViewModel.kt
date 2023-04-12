package com.progressterra.ipbandroidview.pages.cart

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.proshkacartcard.ProshkaCartCardEvent
import com.progressterra.ipbandroidview.features.proshkatopbar.ProshkaTopBarEvent
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

    fun refresh() = intent {
        var isSuccess = true
        cartUseCase().onSuccess {
            reduce { CartState() }
        }.onFailure {
            isSuccess = false
        }
        reduce { state.updateScreenState(ScreenState.fromBoolean(isSuccess)) }
    }

    override fun handle(event: ProshkaCartCardEvent) = intent {
        when (event) {
            is ProshkaCartCardEvent.Open -> postSideEffect(CartEvent.OnItem(event.id))
            is ProshkaCartCardEvent.RemoveFromCart -> removeFromCartUseCase(event.id).onSuccess {
                refresh()
            }
        }
    }

    override fun handle(event: ProshkaTopBarEvent) {
        when (event) {
            ProshkaTopBarEvent.Back -> Unit
        }
    }

    override fun handle(event: ButtonEvent) = intent {
        when (event) {
            is ButtonEvent.Click -> postSideEffect(CartEvent.Payment)
        }
    }

    override fun handle(event: CounterEvent) = intent {
        when (event) {
            is CounterEvent.Add -> addToCartUseCase(event.id).onSuccess {
                refresh()
            }
            is CounterEvent.Remove -> removeFromCartUseCase(event.id).onSuccess {
                refresh()
            }
        }
    }

    override fun handle(event: StateBoxEvent) = intent {
        when (event) {
            is StateBoxEvent.Refresh -> refresh()
        }
    }
}