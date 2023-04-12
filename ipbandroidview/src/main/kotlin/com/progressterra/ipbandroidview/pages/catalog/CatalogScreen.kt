package com.progressterra.ipbandroidview.pages.catalog

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.search.Search
import com.progressterra.ipbandroidview.features.trace.Trace
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox
import com.progressterra.ipbandroidview.widgets.catalogitems.CatalogItems
import com.progressterra.ipbandroidview.widgets.storeitems.StoreItems

@Composable
fun CatalogScreen(
    state: CatalogState, useComponent: UseCatalog
) {
    ThemedLayout(topBar = {
        if (state.trace.trace.isEmpty()) {
            Search(
                modifier = Modifier.padding(horizontal = 20.dp),
                state = state.search,
                useComponent = useComponent
            )
        } else {
            Trace(
                state = state.trace,
                useComponent = useComponent
            )
        }
    }) { _, _ ->
        StateBox(
            state = state.stateBox, useComponent = useComponent
        ) {
            StoreItems(
                state = state.goods, useComponent = useComponent
            )
            CatalogItems(
                state = state.current, useComponent = useComponent
            )
        }
    }
}