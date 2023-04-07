package com.progressterra.ipbandroidview.pages.favorites

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCardState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import com.progressterra.ipbandroidview.widgets.proshkastoreitems.ProshkaStoreItemsState

@Immutable
data class FavoritesState(
    val items: ProshkaStoreItemsState = ProshkaStoreItemsState(),
    val stateBox: StateBoxState = StateBoxState()
) {

    fun updateScreenState(newScreenState: ScreenState) =
        copy(stateBox = stateBox.updateState(newScreenState))

    fun updateItemsState(newItems: List<ProshkaStoreCardState>) =
        copy(items = items.updateItems(newItems))
}
