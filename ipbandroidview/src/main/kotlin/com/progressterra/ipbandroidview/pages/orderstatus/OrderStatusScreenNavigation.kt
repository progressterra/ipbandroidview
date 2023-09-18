package com.progressterra.ipbandroidview.pages.orderstatus

import com.progressterra.ipbandroidview.shared.mvi.OnBack
import com.progressterra.ipbandroidview.shared.mvi.OnOrderDetails

interface OrderStatusScreenNavigation : OnBack, OnOrderDetails {

    fun onMain()
}