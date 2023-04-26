package com.progressterra.ipbandroidview.ui.orders

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.OrderDetails

@Immutable
data class OrdersState(
    val orders: List<OrderDetails> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING
)