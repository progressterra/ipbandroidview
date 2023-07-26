package com.progressterra.ipbandroidview.pages.orderlist

import androidx.compose.runtime.Immutable
import arrow.optics.optics
import com.progressterra.ipbandroidview.features.ordercompact.OrderCompactState
import com.progressterra.ipbandroidview.shared.ScreenState

@Immutable
@optics
data class OrdersListState(
    val orders: List<OrderCompactState> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING
) { companion object }