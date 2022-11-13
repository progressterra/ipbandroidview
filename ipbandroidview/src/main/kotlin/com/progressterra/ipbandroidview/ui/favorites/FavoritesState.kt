package com.progressterra.ipbandroidview.ui.favorites

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.StoreGoods

@Immutable
data class FavoritesState(
    val items: List<StoreGoods> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING
)
