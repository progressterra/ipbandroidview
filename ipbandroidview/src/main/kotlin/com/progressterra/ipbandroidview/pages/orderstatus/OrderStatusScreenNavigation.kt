package com.progressterra.ipbandroidview.pages.orderstatus

import com.progressterra.ipbandroidview.pages.nav.OnBack
import com.progressterra.ipbandroidview.pages.nav.OnOrderDetails

interface OrderStatusScreenNavigation : OnBack, OnOrderDetails {

    fun onMain()
}