package com.progressterra.ipbandroidview.pages.payment

import com.progressterra.ipbandroidview.pages.orderstatus.OrderStatusScreenState
import com.progressterra.ipbandroidview.shared.mvi.OnBack

interface PaymentScreenNavigation : OnBack {

    fun onPaymentStatus(data: OrderStatusScreenState)
}