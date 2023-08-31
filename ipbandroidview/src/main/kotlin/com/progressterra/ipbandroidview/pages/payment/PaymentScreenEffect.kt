package com.progressterra.ipbandroidview.pages.payment

import com.progressterra.ipbandroidview.pages.orderstatus.OrderStatusState

sealed class PaymentScreenEffect {

    data object Back : PaymentScreenEffect()

    class Next(val data: OrderStatusState) : PaymentScreenEffect()
}
