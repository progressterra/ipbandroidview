package com.progressterra.ipbandroidview.ui.catalog

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.components.SearchBoxState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.dto.Category
import com.progressterra.ipbandroidview.dto.Goods
import com.progressterra.ipbandroidview.dto.component.Screen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Immutable
data class CatalogState(
    val categories: List<Category> = emptyList(),
    val goods: Flow<PagingData<Goods>> = emptyFlow(),
    override val keyword: String = "",
    override val searchGoods: Flow<PagingData<Goods>> = emptyFlow(),
    override val screenState: ScreenState = ScreenState.LOADING
) : Screen, SearchBoxState