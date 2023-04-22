package com.progressterra.ipbandroidview.pages.payment

import com.progressterra.ipbandroidview.pages.orderstatus.OrderStatusState

sealed class PaymentEvent {

    object Back : PaymentEvent()

    class Next(val orderStatusState: OrderStatusState) : PaymentEvent()
}
