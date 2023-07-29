package com.progressterra.ipbandroidview.pages.favorites

import androidx.compose.runtime.Immutable
import arrow.optics.optics
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.widgets.storeitems.StoreItemsState

@Immutable
@optics data class FavoritesState(
    val items: StoreItemsState = StoreItemsState(),
    val stateBox: ScreenState = ScreenState.LOADING
) { companion object }
