package com.progressterra.ipbandroidview.pages.orderdetails

import com.progressterra.ipbandroidview.features.ordertracking.OrderTrackingState

sealed class OrderDetailsScreenEffect {

    data object Back : OrderDetailsScreenEffect()

    class OpenGoods(val data: String) : OrderDetailsScreenEffect()

    class Tracking(val data: OrderTrackingState) : OrderDetailsScreenEffect()
}