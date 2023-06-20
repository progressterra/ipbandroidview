package com.progressterra.ipbandroidview.pages.orderdetails

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsState

@Immutable
data class OrderDetailsScreenState(
    val details: OrderDetailsState = OrderDetailsState()
)