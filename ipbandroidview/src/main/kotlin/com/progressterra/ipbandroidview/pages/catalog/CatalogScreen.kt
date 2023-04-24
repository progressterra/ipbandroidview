package com.progressterra.ipbandroidview.pages.catalog

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.features.search.Search
import com.progressterra.ipbandroidview.features.search.SearchState
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.features.trace.Trace
import com.progressterra.ipbandroidview.features.trace.TraceState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox
import com.progressterra.ipbandroidview.widgets.catalogitems.CatalogItems
import com.progressterra.ipbandroidview.widgets.storeitems.StoreItems
import com.progressterra.ipbandroidview.widgets.storeitems.StoreItemsState
import kotlinx.coroutines.flow.flowOf

@Composable
fun CatalogScreen(
    state: CatalogState, useComponent: UseCatalog
) {
    ThemedLayout(topBar = {
        if (state.trace.trace.size <= 1) {
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

@Composable
@Preview
private fun CatalogScreenPreview() {
    IpbTheme {
        CatalogScreen(
            state = CatalogState(
                trace = TraceState(trace = listOf()),
                stateBox = ScreenState.SUCCESS,
                search = SearchState(
                    text = "aliquam",
                    enabled = false
                ),
                current = CatalogCardState(
                    id = "liber",
                    name = "Dick Carter",
                    imageUrl = "http://www.bing.com/search?q=libris",
                    children = listOf()
                ),
                goods = StoreItemsState.Flowed(
                    items =
                    flowOf(
                        PagingData.from(
                            listOf(
                                StoreCardState(
                                    id = "Kotek",
                                    name = "Weston",
                                    company = "Convallis",
                                    price = SimplePrice(1000),
                                    imageUrl = "https://placekitten.com/200/300",
                                    loan = "Рассрочка: 2 платежа по 150 ₽"
                                ),
                                StoreCardState(
                                    id = "Kotek 2",
                                    name = "Weston",
                                    company = "Convallis",
                                    price = SimplePrice(2000),
                                    imageUrl = "https://placekitten.com/200/300",
                                    loan = "Рассрочка: 2 платежа по 150 ₽"
                                ),
                                StoreCardState(
                                    id = "Kotek 3",
                                    name = "Nombre",
                                    company = "Convallis",
                                    price = SimplePrice(5000),
                                    imageUrl = "https://placekitten.com/200/300",
                                    loan = "Рассрочка: 2 платежа по 150 ₽"
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