package com.progressterra.ipbandroidview.ui.favorites

import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.dto.Goods

data class FavoritesState(
    val items: List<Goods> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING
)
