package com.progressterra.ipbandroidview.pages.catalog

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.features.search.SearchState
import com.progressterra.ipbandroidview.features.trace.TraceState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.widgets.storeitems.StoreItemsState

@Immutable
data class CatalogState(
    val trace: TraceState = TraceState(),
    val screen: StateColumnState = StateColumnState(),
    val search: SearchState = SearchState(),
    val current: CatalogCardState = CatalogCardState(),
    val goods: StoreItemsState = StoreItemsState()
)