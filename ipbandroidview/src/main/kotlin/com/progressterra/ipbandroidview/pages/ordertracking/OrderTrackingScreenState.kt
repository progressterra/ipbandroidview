package com.progressterra.ipbandroidview.pages.ordertracking

import com.progressterra.ipbandroidview.features.ordertracking.OrderTrackingState


data class OrderTrackingScreenState(
    val tracking: OrderTrackingState = OrderTrackingState()
) {

    companion object
}