package com.progressterra.ipbandroidview.pages.orderlist

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import arrow.optics.optics
import com.progressterra.ipbandroidview.features.ordercompact.OrderCompactState
import com.progressterra.ipbandroidview.shared.ScreenState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Immutable
@optics
data class OrdersListState(
    val orders: Flow<PagingData<OrderCompactState>> = emptyFlow(),
    val screenState: ScreenState = ScreenState.LOADING
) { companion object }