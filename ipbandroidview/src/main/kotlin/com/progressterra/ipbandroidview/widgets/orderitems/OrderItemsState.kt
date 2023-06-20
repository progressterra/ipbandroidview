package com.progressterra.ipbandroidview.widgets.orderitems

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.ordercard.OrderCardState

@Immutable
data class OrderItemsState(
    val items: List<OrderCardState> = emptyList()
)