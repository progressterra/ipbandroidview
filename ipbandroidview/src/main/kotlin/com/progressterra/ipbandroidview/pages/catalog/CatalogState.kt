package com.progressterra.ipbandroidview.pages.catalog

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.features.search.SearchState
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.features.trace.TraceState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import com.progressterra.ipbandroidview.widgets.storeitems.StoreItemsState
import kotlinx.coroutines.flow.Flow

data class CatalogState(
    val trace: TraceState = TraceState(),
    val stateBox: StateBoxState = StateBoxState(),
    val search: SearchState = SearchState(),
    val current: CatalogCardState = CatalogCardState(),
    val goods: StoreItemsState.Flowed = StoreItemsState.Flowed()
) {

    fun updateGoods(flow: Flow<PagingData<StoreCardState>>) = copy(goods = goods.updateItems(flow))

    fun addTrace(newTrace: CatalogCardState) = copy(trace = trace.addTrace(newTrace))

    fun updateItems(newCurrent: CatalogCardState) = copy(current = newCurrent)

    fun removeTrace() = copy(trace = trace.removeTrace())
}