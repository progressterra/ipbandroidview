package com.progressterra.ipbandroidview.pages.orders

import com.progressterra.ipbandroidview.shared.mvi.OnBack

interface OrdersScreenNavigation : OnBack {

    fun onNext(data: String)
}