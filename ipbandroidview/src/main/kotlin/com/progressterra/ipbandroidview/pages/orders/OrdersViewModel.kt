package com.progressterra.ipbandroidview.pages.orders

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.ordercard.OrderCardEvent
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsEvent
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsState
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class OrdersViewModel : ViewModel(), ContainerHost<OrdersState, OrdersEvent>, UseOrders {

    override val container: Container<OrdersState, OrdersEvent> = container(OrdersState())

    fun refresh(newState: OrderDetailsState) {
        intent {
            reduce { state.uOrder(newState) }
        }
    }

    override fun handle(event: OrderDetailsEvent) {
        intent {
            postSideEffect(OrdersEvent.Tracking)
        }
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
            postSideEffect(OrdersEvent.Back)
        }
    }
}