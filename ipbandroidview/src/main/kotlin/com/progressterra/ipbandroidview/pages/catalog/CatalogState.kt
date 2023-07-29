package com.progressterra.ipbandroidview.pages.catalog

import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.features.search.SearchState
import com.progressterra.ipbandroidview.features.trace.TraceState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.widgets.storeitems.StoreItemsState

data class CatalogState(
    val trace: TraceState = TraceState(),
    val stateBox: ScreenState = ScreenState.LOADING,
    val search: SearchState = SearchState(),
    val current: CatalogCardState = CatalogCardState(),
    val goods: StoreItemsState = StoreItemsState()
)