package com.progressterra.ipbandroidview.pages.catalog

import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.features.search.SearchState
import com.progressterra.ipbandroidview.features.trace.TraceState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.widgets.storeitems.StoreItemsState

data class CatalogScreenState(
    val trace: TraceState = TraceState(),
    val screen: StateColumnState = StateColumnState(),
    val search: SearchState = SearchState(),
    val current: CatalogCardState = CatalogCardState(),
    val goods: StoreItemsState = StoreItemsState()
)