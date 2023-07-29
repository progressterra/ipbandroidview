package com.progressterra.ipbandroidview.pages.orderstatus

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent

class OrderStatusViewModel : BaseViewModel<OrderStatusState, OrderStatusEvent>(), UseOrderStatus {

    override val initialState = OrderStatusState()

    fun setup(orderStatusState: OrderStatusState) {
        fastEmitState { orderStatusState }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(OrderStatusEvent.OnBack)
    }

    override fun handle(event: ButtonEvent) {
        when (event.id) {
            "main" -> postEffect(OrderStatusEvent.OnMain)
        }
    }
}

