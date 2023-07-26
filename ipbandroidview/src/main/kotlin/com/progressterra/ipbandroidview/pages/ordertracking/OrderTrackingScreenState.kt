package com.progressterra.ipbandroidview.pages.ordertracking

import androidx.compose.runtime.Immutable
import arrow.optics.optics
import com.progressterra.ipbandroidview.features.ordertracking.OrderTrackingState

@Immutable
@optics
data class OrderTrackingScreenState(
    val tracking: OrderTrackingState = OrderTrackingState()
) {

    companion object
}