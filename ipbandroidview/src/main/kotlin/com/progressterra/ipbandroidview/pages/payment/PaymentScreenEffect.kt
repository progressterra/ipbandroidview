package com.progressterra.ipbandroidview.pages.payment

import com.progressterra.ipbandroidview.pages.orderstatus.OrderStatusScreenState

sealed class PaymentScreenEffect {

    data object Back : PaymentScreenEffect()

    class Next(val data: OrderStatusScreenState) : PaymentScreenEffect()
}
