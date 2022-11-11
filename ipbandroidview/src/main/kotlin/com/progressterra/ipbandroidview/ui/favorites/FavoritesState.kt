package com.progressterra.ipbandroidview.ui.favorites

import com.progressterra.ipbandroidview.components.StateBoxState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.Goods

data class FavoritesState(
    val items: List<Goods> = emptyList(),
    override val screenState: ScreenState = ScreenState.LOADING
) : StateBoxState
