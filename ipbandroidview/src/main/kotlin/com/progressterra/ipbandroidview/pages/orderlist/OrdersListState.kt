package com.progressterra.ipbandroidview.pages.orderlist

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.features.ordercompact.OrderCompactState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Immutable
data class OrdersListState(
    val orders: Flow<PagingData<OrderCompactState>> = emptyFlow(),
    val screen: StateBoxState = StateBoxState()
)