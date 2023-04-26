package com.progressterra.ipbandroidview.pages.orders

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class OrdersViewModel(
    private val ordersUseCase: OrdersUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase
) : ViewModel(), ContainerHost<OrdersState, OrdersEvent>, UseOrders {

    override val container: Container<OrdersState, OrdersEvent> = container(OrdersState())

    fun refresh() = intent {
        reduce { state.uScreenState(ScreenState.LOADING) }
        ordersUseCase().onSuccess {
            reduce { state.uScreenState(ScreenState.SUCCESS).uItemsState(it) }
        }.onFailure {
            reduce { state.uScreenState(ScreenState.ERROR) }
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


    override fun handle(event: StoreCardEvent) = intent {
        when (event) {
            is StoreCardEvent.Open -> postSideEffect(OrdersEvent.GoodsDetails(event.id))
            is StoreCardEvent.AddToCart -> addToCartUseCase(event.id).onSuccess {
                refresh()
            }
        }
    }

    override fun handle(event: TopBarEvent) = intent {
        when (event) {
            is TopBarEvent.Back -> postSideEffect(OrdersEvent.Back)
        }
    }

    override fun handle(event: StateBoxEvent) = intent {
        when (event) {
            is StateBoxEvent.Refresh -> refresh()
        }
    }
}