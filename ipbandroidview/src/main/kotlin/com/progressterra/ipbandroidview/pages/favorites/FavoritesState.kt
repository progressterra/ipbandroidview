package com.progressterra.ipbandroidview.pages.favorites

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumnState
import com.progressterra.ipbandroidview.widgets.storeitems.StoreItemsState

@Immutable
data class FavoritesState(
    val items: StoreItemsState = StoreItemsState(),
    val screen: StateColumnState = StateColumnState()
)
