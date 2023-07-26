package com.progressterra.ipbandroidview.pages.catalog

import arrow.optics.optics
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.features.search.SearchState
import com.progressterra.ipbandroidview.features.trace.TraceState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.widgets.storeitems.StoreItemsState

@optics data class CatalogState(
    val trace: TraceState = TraceState(),
    val stateBox: ScreenState = ScreenState.LOADING,
    val search: SearchState = SearchState(),
    val current: CatalogCardState = CatalogCardState(),
    val goods: StoreItemsState.Flowed = StoreItemsState.Flowed()
) { companion object }