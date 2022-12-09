package com.progressterra.ipbandroidview.ui.goods

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.composable.GoodsSearchBarState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.store.Filter
import com.progressterra.ipbandroidview.model.store.StoreGoods
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class GoodsState(
    val currentCategory: String = "",
    override val keyword: String = "",
    val filters: List<Filter> = emptyList(),
    val itemsFlow: Flow<PagingData<StoreGoods>> = emptyFlow(),
    val items: List<StoreGoods> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING
) : GoodsSearchBarState
