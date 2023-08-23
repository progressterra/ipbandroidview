package com.progressterra.ipbandroidview.pages.ordertracking

import com.progressterra.ipbandroidview.features.ordertracking.OrderTrackingState
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.mvi.BaseViewModel

class OrderTrackingScreenViewModel :
    BaseViewModel<OrderTrackingScreenState, OrderTrackingScreenEvent>(), UseOrderTrackingScreen {

    override fun createInitialState() = OrderTrackingScreenState()

    fun setup(newState: OrderTrackingState) {
        emitState { it.copy(tracking = newState) }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(OrderTrackingScreenEvent)
    }
}