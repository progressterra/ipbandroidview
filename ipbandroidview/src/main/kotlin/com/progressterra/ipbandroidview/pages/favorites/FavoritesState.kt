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

    fun updateScreenState(newScreenState: ScreenState) = copy(stateBox = newScreenState)

    fun updateItemsState(newItems: List<StoreCardState>) =
        copy(items = items.updateItems(newItems))
}
