package com.progressterra.ipbandroidview.ui.goods

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.composable.component.GoodsBarComponentState
import com.progressterra.ipbandroidview.composable.component.StoreCardComponentState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.Filter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class GoodsState(
    val currentCategory: String = "",
    val goodsBarComponentState: GoodsBarComponentState = GoodsBarComponentState(),
    val filters: List<Filter> = emptyList(),
    val itemsFlow: Flow<PagingData<StoreCardComponentState>> = emptyFlow(),
    val items: List<StoreCardComponentState> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING
)
