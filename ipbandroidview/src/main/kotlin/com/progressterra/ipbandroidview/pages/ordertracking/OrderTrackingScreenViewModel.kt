package com.progressterra.ipbandroidview.pages.ordertracking

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.ordertracking.OrderTrackingState
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class OrderTrackingScreenViewModel :
    ContainerHost<OrderTrackingScreenState, OrderTrackingScreenEvent>,
    ViewModel(), UseOrderTrackingScreen {

    override val container =
        container<OrderTrackingScreenState, OrderTrackingScreenEvent>(OrderTrackingScreenState())

    fun setup(newState: OrderTrackingState) {
        blockingIntent {
            reduce { OrderTrackingScreenState.tracking.set(state, newState) }
        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            postSideEffect(OrderTrackingScreenEvent)
        }
    }
}