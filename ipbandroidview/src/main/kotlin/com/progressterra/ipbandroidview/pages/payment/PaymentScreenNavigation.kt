package com.progressterra.ipbandroidview.pages.payment

import com.progressterra.ipbandroidview.pages.orderstatus.OrderStatusScreenState
import com.progressterra.ipbandroidview.pages.nav.OnBack

interface PaymentScreenNavigation : OnBack {

    fun onPaymentStatus(data: OrderStatusScreenState)
}