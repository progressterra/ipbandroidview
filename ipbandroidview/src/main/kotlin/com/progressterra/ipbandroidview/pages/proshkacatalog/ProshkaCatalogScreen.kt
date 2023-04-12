package com.progressterra.ipbandroidview.pages.proshkacatalog

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.proshkasearch.ProshkaSearch
import com.progressterra.ipbandroidview.features.proshkasearch.SearchState
import com.progressterra.ipbandroidview.features.proshkasearch.UseProshkaSearch
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox
import com.progressterra.ipbandroidview.widgets.catalogitems.CatalogItems
import com.progressterra.ipbandroidview.widgets.catalogitems.CatalogItemsState
import com.progressterra.ipbandroidview.widgets.catalogitems.UseCatalogItems

@Immutable
data class CatalogState(
    val stateBox: StateBoxState = StateBoxState(),
    val search: SearchState = SearchState(),
    val items: CatalogItemsState = CatalogItemsState()
)

interface ProshkaCatalogScreenInteractor : UseProshkaSearch, UseCatalogItems, UseStateBox

@Composable
fun ProshkaCatalogScreen(
    state: CatalogState, interactor: ProshkaCatalogScreenInteractor
) {
    ThemedLayout(
        topBar = {
            ProshkaSearch(
                modifier = Modifier.padding(horizontal = 20.dp),
                state = state.search, useComponent = interactor
            )
        }
    ) { _, _ ->
        StateBox(
            state = state.stateBox, useComponent = interactor
        ) {
            CatalogItems(
                state = state.items,
                useComponent = interactor
            )
        }
    }
}