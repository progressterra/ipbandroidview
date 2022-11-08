package com.progressterra.ipbandroidview.ui.main

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.dto.Filter
import com.progressterra.ipbandroidview.dto.Goods
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

//TODO own screenstate for search

@Immutable
data class MainState(
    val items: Flow<PagingData<Goods>> = emptyFlow(),
    override val filters: List<Filter> = emptyList(),
    override val keyword: String = "",
    override val searchGoods: List<Goods> = emptyList(),
    override val screenState: ScreenState = ScreenState.SUCCESS
) : SearchState