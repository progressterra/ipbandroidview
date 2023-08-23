package com.progressterra.ipbandroidview.pages.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCard
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.features.search.Search
import com.progressterra.ipbandroidview.features.search.SearchState
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.features.trace.Trace
import com.progressterra.ipbandroidview.features.trace.TraceState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumnState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumn
import com.progressterra.ipbandroidview.widgets.storeitems.StoreItems
import com.progressterra.ipbandroidview.widgets.storeitems.StoreItemsState
import kotlinx.coroutines.flow.flowOf

@Composable
fun CatalogScreen(
    state: CatalogState, useComponent: UseCatalog
) {
    ThemedLayout(topBar = {
        if (state.trace.trace.isNotEmpty()) {
            Trace(
                state = state.trace,
                useComponent = useComponent
            )
        } else {
            Search(
                modifier = Modifier.padding(horizontal = 20.dp),
                state = state.search,
                useComponent = useComponent
            )
        }
    }) { _, _ ->
        StateColumn(
            state = state.screen, useComponent = useComponent
        ) {
            Box {
                StoreItems(
                    modifier = Modifier.zIndex(1f),
                    state = state.goods, useComponent = useComponent
                )
                LazyVerticalGrid(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 40.dp)
                ) {
                    items(state.current.children) {
                        Box(contentAlignment = Alignment.Center) {
                            CatalogCard(
                                state = it, useComponent = useComponent
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun CatalogScreenPreview() {
    IpbTheme {
        CatalogScreen(
            state = CatalogState(
                trace = TraceState(trace = listOf()),
                screen = StateColumnState(state = ScreenState.SUCCESS),
                search = SearchState(
                    text = "aliquam"
                ),
                current = CatalogCardState(
                    id = "liber",
                    name = "Dick Carter",
                    image = "http://www.bing.com/search?q=libris",
                    children = listOf()
                ),
                goods = StoreItemsState(
                    items =
                    flowOf(
                        PagingData.from(
                            listOf(
                                StoreCardState(
                                    id = "Kotek",
                                    name = "Weston",
                                    price = SimplePrice(1000),
                                    image = "https://placekitten.com/200/300",
                                ),
                                StoreCardState(
                                    id = "Kotek 2",
                                    name = "Weston",
                                    price = SimplePrice(2000),
                                    image = "https://placekitten.com/200/300",
                                ),
                                StoreCardState(
                                    id = "Kotek 3",
                                    name = "Nombre",
                                    price = SimplePrice(5000),
                                    image = "https://placekitten.com/200/300",
                                )
                            )
                        )
                    )
                )

            ),
            useComponent = UseCatalog.Empty()
        )
    }
}