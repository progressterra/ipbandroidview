package com.progressterra.ipbandroidview.ui.orders

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.entities.OrderDetails

@Immutable
data class OrdersState(
    val orders: List<OrderDetails> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING
)