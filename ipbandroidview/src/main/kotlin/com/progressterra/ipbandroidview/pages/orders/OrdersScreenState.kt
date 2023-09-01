package com.progressterra.ipbandroidview.pages.orders

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.features.ordercompact.OrderCompactState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class OrdersScreenState(
    val orders: Flow<PagingData<OrderCompactState>> = emptyFlow(),
    val screen: StateColumnState = StateColumnState()
)