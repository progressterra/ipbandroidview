package com.progressterra.ipbandroidview.pages.catalog

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.features.search.SearchState
import com.progressterra.ipbandroidview.features.trace.TraceState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.widgets.storeitems.StoreItemsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CatalogState(
    val trace: TraceState = TraceState(),
    val stateBox: ScreenState = ScreenState.LOADING,
    val search: SearchState = SearchState(),
    val items: Flow<PagingData<CatalogCardState>> = emptyFlow(),
    val current: CatalogCardState = CatalogCardState(),
    val goods: StoreItemsState = StoreItemsState()
)