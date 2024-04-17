package com.progressterra.ipbandroidview.pages.payment

import com.progressterra.ipbandroidview.pages.nav.OnBack
import com.progressterra.ipbandroidview.pages.orderstatus.OrderStatusScreenState

interface PaymentScreenNavigation : OnBack {

    fun onPaymentStatus(data: OrderStatusScreenState)
}
