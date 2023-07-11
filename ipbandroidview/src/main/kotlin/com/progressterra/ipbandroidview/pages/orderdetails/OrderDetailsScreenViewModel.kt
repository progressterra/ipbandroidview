package com.progressterra.ipbandroidview.pages.orderdetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.entities.toOrderTrackingState
import com.progressterra.ipbandroidview.features.ordercard.OrderCardEvent
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class OrderDetailsScreenViewModel : ContainerHost<OrderDetailsScreenState, OrderDetailsScreenEvent>,
    ViewModel(), UseOrderDetailsScreen {

    override val container = container<OrderDetailsScreenState, OrderDetailsScreenEvent>(
        OrderDetailsScreenState()
    )

    override fun handle(event: OrderCardEvent) {
        intent {
            postSideEffect(OrderDetailsScreenEvent.OpenGoods(event.id))
        }
    }

    override fun handle(event: OrderDetailsEvent) {
        intent {
            postSideEffect(OrderDetailsScreenEvent.Tracking(state.details.toOrderTrackingState()))
        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            postSideEffect(OrderDetailsScreenEvent.Back)
        }
    }
}