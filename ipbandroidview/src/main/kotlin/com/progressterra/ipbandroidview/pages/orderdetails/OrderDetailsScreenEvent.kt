package com.progressterra.ipbandroidview.pages.orderdetails

import com.progressterra.ipbandroidview.features.ordertracking.OrderTrackingState

sealed class OrderDetailsScreenEvent {

    object Back : OrderDetailsScreenEvent()

    class OpenGoods(val goodsId: String) : OrderDetailsScreenEvent()

    class Tracking(val tracking: OrderTrackingState) : OrderDetailsScreenEvent()
}