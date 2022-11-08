package com.progressterra.ipbandroidview.ui.catalog

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.dto.Category
import com.progressterra.ipbandroidview.dto.Filter
import com.progressterra.ipbandroidview.dto.Goods
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Immutable
data class CatalogState(
    val currentCategory: String? = null,
    val categories: List<Category> = emptyList(),
    val goods: Flow<PagingData<Goods>> = emptyFlow(),
    val screenState: ScreenState,
    override val keyword: String = "",
    override val filters: List<Filter> = emptyList(),
    override val searchGoods: List<Goods> = emptyList(),
    override val searchScreenState: ScreenState = ScreenState.LOADING
) : SearchState