package com.progressterra.ipbandroidview.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.progressterra.ipbandroidview.components.SearchBox
import com.progressterra.ipbandroidview.components.StateBox
import com.progressterra.ipbandroidview.components.StoreCard
import com.progressterra.ipbandroidview.components.ThemedLayout
import com.progressterra.ipbandroidview.components.topbar.SearchTopBar
import com.progressterra.ipbandroidview.components.utils.items
import com.progressterra.ipbandroidview.model.Goods
import com.progressterra.ipbandroidview.model.GoodsColor
import com.progressterra.ipbandroidview.model.GoodsSize
import com.progressterra.ipbandroidview.theme.AppTheme
import com.progressterra.ipbandroidview.ui.search.SearchInteractor
import com.progressterra.ipbandroidview.ui.search.SearchState
import kotlinx.coroutines.flow.flowOf

@Composable
fun MainScreen(
    mainState: MainState,
    mainInteractor: MainInteractor,
    searchState: SearchState,
    searchInteractor: SearchInteractor
) {
    ThemedLayout(topBar = {
        SearchTopBar(
            state = searchState,
            onBack = { searchInteractor.back() },
            onKeyword = { searchInteractor.keyword(it) },
            onSearch = { searchInteractor.search() },
            onFilters = {}, full = false
        )
    }) { _, _ ->
        SearchBox(
            state = searchState,
            onRefresh = { searchInteractor.refresh() },
            onFavorite = { searchInteractor.favorite(it) },
            onGoods = { searchInteractor.goodsDetails(it) }) {
            StateBox(
                state = mainState.screenState,
                onRefresh = { mainInteractor.refresh() }) {
                val lazyItems: LazyPagingItems<Goods> = mainState.items.collectAsLazyPagingItems()
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxSize(),
                    columns = GridCells.Fixed(AppTheme.customization.catalogStyle.columns),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium),
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium),
                    contentPadding = PaddingValues(AppTheme.dimensions.medium)
                ) {
                    items(lazyItems) { goods ->
                        goods?.let {
                            StoreCard(modifier = Modifier.align(Alignment.Center),
                                state = goods,
                                onClick = { mainInteractor.goodsDetails(goods) },
                                onFavorite = {
                                    mainInteractor.favorite(
                                        goods
                                    )
                                })
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    AppTheme {
        MainScreen(
            mainState = MainState(
                items = flowOf(
                    PagingData.from(
                        listOf(
                            Goods(
                                id = "",
                                name = "SOME cool 1",
                                description = "",
                                price = "3550 USD",
                                favorite = false,
                                images = listOf(),
                                parameters = listOf(),
                                inCartCounter = 0,
                                image = "",
                                color = GoodsColor("", ""),
                                sizes = emptyList(),
                                size = GoodsSize(false, "", ""),
                                colors = emptyList()
                            ), Goods(
                                id = "",
                                name = "cool 2",
                                description = "",
                                price = "3550 USD",
                                favorite = false,
                                images = listOf(),
                                parameters = listOf(),
                                inCartCounter = 0,
                                image = "",
                                color = GoodsColor("", ""),
                                sizes = emptyList(),
                                size = GoodsSize(false, "", ""),
                                colors = emptyList()
                            ), Goods(
                                id = "",
                                name = "cool 3",
                                description = "",
                                price = "3550 USD",
                                favorite = false,
                                images = listOf(),
                                parameters = listOf(),
                                inCartCounter = 0,
                                image = "",
                                color = GoodsColor("", ""),
                                sizes = emptyList(),
                                size = GoodsSize(false, "", ""),
                                colors = emptyList()
                            ), Goods(
                                id = "",
                                name = "COME SOOL",
                                description = "",
                                price = "3550 USD",
                                favorite = false,
                                images = listOf(),
                                parameters = listOf(),
                                inCartCounter = 0,
                                image = "",
                                color = GoodsColor("", ""),
                                sizes = emptyList(),
                                size = GoodsSize(false, "", ""),
                                colors = emptyList()
                            )
                        )
                    )
                )
            ), mainInteractor = MainInteractor.Empty(),
            searchState = SearchState(),
            searchInteractor = SearchInteractor.Empty()
        )
    }
}