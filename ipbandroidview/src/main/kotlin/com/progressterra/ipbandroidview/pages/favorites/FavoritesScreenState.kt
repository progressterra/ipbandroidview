package com.progressterra.ipbandroidview.pages.favorites

import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.widgets.storeitems.StoreItemsState

data class FavoritesScreenState(
    val items: StoreItemsState = StoreItemsState(),
    val screen: StateColumnState = StateColumnState()
)
