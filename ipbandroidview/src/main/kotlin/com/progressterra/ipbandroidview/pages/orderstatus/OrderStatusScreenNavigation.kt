package com.progressterra.ipbandroidview.pages.orderstatus

import com.progressterra.ipbandroidview.shared.mvi.OnBack

interface OrderStatusScreenNavigation : OnBack {

    fun onOrderDetails(data: String)

    fun onMain()
}