package com.progressterra.ipbandroidview.pages.orders

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.ordercard.OrderCardEvent
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class OrdersViewModel(
    private val ordersUseCase: OrdersUseCase
) : ViewModel(), ContainerHost<OrdersState, OrdersEvent>, UseOrders {

    override val container: Container<OrdersState, OrdersEvent> = container(OrdersState())

    fun refresh() {
        intent {
            reduce { state.copy(screenState = ScreenState.LOADING) }
            ordersUseCase().onSuccess {
                reduce { state.copy(screenState = ScreenState.SUCCESS, orders = it) }
            }.onFailure {
                reduce { state.copy(screenState = ScreenState.ERROR) }
            }
        }
    }

    override fun handle(event: OrderDetailsEvent) {
        TODO("Not yet implemented")
    }

    override fun handle(event: OrderCardEvent) {
        intent {
            when (event) {
                is OrderCardEvent.GoodsDetails -> postSideEffect(OrdersEvent.GoodsDetails(event.id))
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            when (event) {
                is TopBarEvent.Back -> postSideEffect(OrdersEvent.Back)
            }
        }
    }

    override fun handle(event: StateBoxEvent) {
        intent {
            when (event) {
                is StateBoxEvent.Refresh -> refresh()
            }
        }
    }
}