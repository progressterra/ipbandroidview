package com.progressterra.ipbandroidview.pages.orderstatus

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.mvi.BaseViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent

class OrderStatusViewModel : BaseViewModel<OrderStatusState, OrderStatusEvent>(), UseOrderStatus {

    override fun createInitialState() = OrderStatusState()

    fun setup(orderStatusState: OrderStatusState) {
        emitState { orderStatusState }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(OrderStatusEvent.OnBack)
    }

    override fun handle(event: ButtonEvent) {
        if (event.id == "main") {
            postEffect(OrderStatusEvent.OnMain)
        } else if (event.id == "order") {
            postEffect(OrderStatusEvent.OnOrder(currentState.id))
        }
    }
}

