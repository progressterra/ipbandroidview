package com.progressterra.ipbandroidview.pages.catalog

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.search.Search
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox
import com.progressterra.ipbandroidview.widgets.catalogitems.CatalogItems

@Composable
fun CatalogScreen(
    state: CatalogState, useComponent: UseCatalog
) {
    ThemedLayout(topBar = {
        Search(
            modifier = Modifier.padding(horizontal = 20.dp),
            state = state.search,
            useComponent = useComponent
        )
    }) { _, _ ->
        StateBox(
            state = state.stateBox, useComponent = useComponent
        ) {
            CatalogItems(
                state = state.items, useComponent = useComponent
            )
        }
    }
}