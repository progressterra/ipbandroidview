package com.progressterra.ipbandroidview.pages.orderdetails

import com.progressterra.ipbandroidview.features.ordertracking.OrderTrackingState
import com.progressterra.ipbandroidview.pages.nav.OnBack
import com.progressterra.ipbandroidview.pages.nav.OnGoodsDetails

interface OrderDetailsScreenNavigation : OnBack, OnGoodsDetails {

    fun onTracking(data: OrderTrackingState)
}