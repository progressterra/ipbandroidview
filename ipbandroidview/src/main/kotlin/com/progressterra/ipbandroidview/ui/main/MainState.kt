package com.progressterra.ipbandroidview.ui.main

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.components.storeitem.StoreItemState
import com.progressterra.ipbandroidview.components.topbar.SearchBarState
import com.progressterra.ipbandroidview.core.ScreenState

@Immutable
data class MainState(
    val searchBarState: SearchBarState = SearchBarState(),
    val items: List<StoreItemState> = emptyList(),
    val screenState: ScreenState = ScreenState.SUCCESS
)