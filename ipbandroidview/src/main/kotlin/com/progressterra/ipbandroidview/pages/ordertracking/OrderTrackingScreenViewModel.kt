package com.progressterra.ipbandroidview.pages.ordertracking

import com.progressterra.ipbandroidview.features.ordertracking.OrderTrackingState
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputViewModel

class OrderTrackingScreenViewModel :
    AbstractInputViewModel<OrderTrackingState, OrderTrackingScreenState, OrderTrackingScreenEffect>(),
    UseOrderTrackingScreen {

    override fun createInitialState() = OrderTrackingScreenState()

    override fun setup(data: OrderTrackingState) {
        emitState { it.copy(tracking = data) }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(OrderTrackingScreenEffect)
    }
}