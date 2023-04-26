package com.progressterra.ipbandroidview.pages.orders

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetails
import com.progressterra.ipbandroidview.shared.ScreenState

@Immutable
data class OrdersState(
    val orders: List<OrderDetails> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING
)