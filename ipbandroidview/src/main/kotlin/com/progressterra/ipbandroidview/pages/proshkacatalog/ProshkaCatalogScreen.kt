package com.progressterra.ipbandroidview.pages.proshkacatalog

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.proshkasearch.ProshkaSearch
import com.progressterra.ipbandroidview.features.proshkasearch.ProshkaSearchState
import com.progressterra.ipbandroidview.features.proshkasearch.UseProshkaSearch
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox
import com.progressterra.ipbandroidview.widgets.proshkacatalogitems.ProshkaCatalogItems
import com.progressterra.ipbandroidview.widgets.proshkacatalogitems.ProshkaCatalogItemsState
import com.progressterra.ipbandroidview.widgets.proshkacatalogitems.UseProshkaCatalogItems

@Immutable
data class ProshkaCatalogState(
    val stateBox: StateBoxState = StateBoxState(),
    val search: ProshkaSearchState = ProshkaSearchState(),
    val items: ProshkaCatalogItemsState = ProshkaCatalogItemsState()
)

interface ProshkaCatalogScreenInteractor : UseProshkaSearch, UseProshkaCatalogItems, UseStateBox

@Composable
fun ProshkaCatalogScreen(
    state: ProshkaCatalogState, interactor: ProshkaCatalogScreenInteractor
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
            ProshkaCatalogItems(
                state = state.items,
                useComponent = interactor
            )
        }
    }
}