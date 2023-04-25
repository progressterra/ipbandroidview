package com.progressterra.ipbandroidview.pages.favorites

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.widgets.storeitems.StoreItemsState

@Immutable
data class FavoritesState(
    val items: StoreItemsState.Listed = StoreItemsState.Listed(),
    val stateBox: ScreenState = ScreenState.LOADING
) {

    fun uScreenState(newScreenState: ScreenState) = copy(stateBox = newScreenState)

    fun uItemsState(newItems: List<StoreCardState>) =
        copy(items = items.uItems(newItems))
}
