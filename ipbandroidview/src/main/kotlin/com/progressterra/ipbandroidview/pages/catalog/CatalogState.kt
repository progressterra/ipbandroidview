package com.progressterra.ipbandroidview.pages.catalog

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.features.search.SearchState
import com.progressterra.ipbandroidview.features.search.uText
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.features.trace.TraceState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.widgets.storeitems.StoreItemsState
import kotlinx.coroutines.flow.Flow

data class CatalogState(
    val trace: TraceState = TraceState(),
    val stateBox: ScreenState = ScreenState.LOADING,
    val search: SearchState = SearchState(),
    val current: CatalogCardState = CatalogCardState(),
    val goods: StoreItemsState.Flowed = StoreItemsState.Flowed()
) {

    fun uSearchText(newText: String) = copy(search = search.uText(newText))

    fun uScreenState(screenState: ScreenState) = copy(stateBox = screenState)

    fun uGoods(flow: Flow<PagingData<StoreCardState>>) = copy(goods = goods.uItems(flow))

    fun addTrace(newTrace: CatalogCardState) = copy(trace = trace.addTrace(newTrace))

    fun uCategory(newCurrent: CatalogCardState) = copy(current = newCurrent)

    fun removeTrace() = copy(trace = trace.removeTrace())
}