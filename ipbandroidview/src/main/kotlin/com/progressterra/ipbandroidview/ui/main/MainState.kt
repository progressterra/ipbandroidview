package com.progressterra.ipbandroidview.ui.main

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.components.topbar.SearchBarState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.dto.GoodsCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Immutable
data class MainState(
    val searchBarState: SearchBarState = SearchBarState(),
    val items: Flow<PagingData<GoodsCard>> = emptyFlow(),
    val screenState: ScreenState = ScreenState.SUCCESS
)