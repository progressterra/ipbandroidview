package com.progressterra.ipbandroidview.pages.catalog

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.search.SearchState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import com.progressterra.ipbandroidview.widgets.catalogitems.CatalogItemsState

@Immutable
data class CatalogState(
    val stateBox: StateBoxState = StateBoxState(),
    val search: SearchState = SearchState(),
    val items: CatalogItemsState = CatalogItemsState()
)