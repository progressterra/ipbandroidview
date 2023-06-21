package com.progressterra.ipbandroidview.pages.orders

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsState

@Immutable
data class OrdersState(
    val order: OrderDetailsState = OrderDetailsState()
) {

    fun uOrder(newOrder: OrderDetailsState) = copy(order = newOrder)
}