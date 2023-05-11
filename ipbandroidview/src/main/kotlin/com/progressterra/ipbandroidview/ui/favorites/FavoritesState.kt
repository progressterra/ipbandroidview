package com.progressterra.ipbandroidview.ui.favorites

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.composable.component.StoreCardComponentState

@Immutable
data class FavoritesState(
    val items: List<StoreCardComponentState> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING
)
