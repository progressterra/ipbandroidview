package com.progressterra.ipbandroidview.pages.orderdetails

import com.progressterra.ipbandroidview.features.ordertracking.OrderTrackingState
import com.progressterra.ipbandroidview.shared.mvi.OnBack
import com.progressterra.ipbandroidview.shared.mvi.OpenGoodsDetails

interface OrderDetailsScreenNavigation : OnBack, OpenGoodsDetails {

    fun onTracking(data: OrderTrackingState)
}