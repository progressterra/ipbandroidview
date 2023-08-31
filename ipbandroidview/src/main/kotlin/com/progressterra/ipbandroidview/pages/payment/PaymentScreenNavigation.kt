package com.progressterra.ipbandroidview.pages.payment

import com.progressterra.ipbandroidview.pages.orderstatus.OrderStatusState
import com.progressterra.ipbandroidview.shared.mvi.OnBack

interface PaymentScreenNavigation : OnBack {

    fun onNext(data: OrderStatusState)
}