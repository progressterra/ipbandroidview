package com.progressterra.ipbandroidview.pages.proshkacatalog

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.features.ProshkaSearch
import com.progressterra.ipbandroidview.features.ProshkaSearchState
import com.progressterra.ipbandroidview.features.UseProshkaSearch
import com.progressterra.ipbandroidview.shared.ui.StateBox
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.UseStateBox
import com.progressterra.ipbandroidview.widgets.ProshkaCatalogItems
import com.progressterra.ipbandroidview.widgets.ProshkaCatalogItemsState
import com.progressterra.ipbandroidview.widgets.UseProshkaCatalogItems

@Immutable
data class ProshkaCatalogState(
    val screenState: ScreenState = ScreenState.LOADING,
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
            state = state.screenState, useComponent = interactor
        ) {
            ProshkaCatalogItems(
                state = state.items,
                useComponent = interactor
            )
        }
    }
}