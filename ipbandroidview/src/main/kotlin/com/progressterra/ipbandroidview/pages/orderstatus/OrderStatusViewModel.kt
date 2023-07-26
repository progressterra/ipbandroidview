package com.progressterra.ipbandroidview.pages.orderstatus

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class OrderStatusViewModel : UseOrderStatus, ViewModel(),
    ContainerHost<OrderStatusState, OrderStatusEvent> {

    override val container = container<OrderStatusState, OrderStatusEvent>(OrderStatusState())

    fun setup(orderStatusState: OrderStatusState) {
        blockingIntent {
            reduce { orderStatusState }
        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            postSideEffect(OrderStatusEvent.OnBack)
        }
    }

    override fun handle(event: ButtonEvent) {
        intent {
            when (event.id) {
                "main" -> postSideEffect(OrderStatusEvent.OnMain)
            }
        }
    }
}

