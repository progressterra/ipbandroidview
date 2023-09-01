package com.progressterra.ipbandroidview.pages.orderstatus

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent

class OrderStatusScreenViewModel :
    AbstractInputViewModel<OrderStatusScreenState, OrderStatusScreenState, OrderStatusScreenEffect>(),
    UseOrderStatusScreen {

    override fun createInitialState() = OrderStatusScreenState()

    override fun setup(data: OrderStatusScreenState) {
        emitState { data }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(OrderStatusScreenEffect.OnBack)
    }

    override fun handle(event: ButtonEvent) {
        if (event.id == "main") {
            postEffect(OrderStatusScreenEffect.OnMain)
        } else if (event.id == "order") {
            postEffect(OrderStatusScreenEffect.OnOrder(currentState.id))
        }
    }
}

