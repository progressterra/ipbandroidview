package com.progressterra.ipbandroidview.pages.ordertracking

import com.progressterra.ipbandroidview.features.ordertracking.OrderTrackingState
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.BaseViewModel

class OrderTrackingScreenViewModel :
    BaseViewModel<OrderTrackingScreenState, OrderTrackingScreenEvent>(), UseOrderTrackingScreen {

    override val initialState = OrderTrackingScreenState()

    fun setup(newState: OrderTrackingState) {
        fastEmitState { it.copy(tracking = newState) }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(OrderTrackingScreenEvent)
    }
}