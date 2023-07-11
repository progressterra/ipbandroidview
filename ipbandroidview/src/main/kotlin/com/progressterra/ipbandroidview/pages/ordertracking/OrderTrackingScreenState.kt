package com.progressterra.ipbandroidview.pages.ordertracking

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.ordertracking.OrderTrackingState

@Immutable
data class OrderTrackingScreenState(
    val tracking: OrderTrackingState = OrderTrackingState()
) {

    fun uTracking(newTracking: OrderTrackingState) = copy(tracking = newTracking)
}