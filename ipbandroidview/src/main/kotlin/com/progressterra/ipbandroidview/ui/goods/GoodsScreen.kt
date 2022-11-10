package com.progressterra.ipbandroidview.ui.goods

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
import com.progressterra.ipbandroidview.components.StateBox
import com.progressterra.ipbandroidview.components.StoreItemCard
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
fun GoodsScreen(
    goodsState: GoodsState,
    goodsInteractor: GoodsInteractor,
    searchState: SearchState,
    searchInteractor: SearchInteractor
) {
    ThemedLayout(topBar = {
        SearchTopBar(state = searchState,
            onBack = { searchInteractor.back() },
            onKeyword = { searchInteractor.keyword(it) },
            onSearch = { searchInteractor.search() },
            onFilters = {})
    }) { _, _ ->
        StateBox(
            state = goodsState.screenState,
            onRefresh = { goodsInteractor.refresh() }) {
            val lazyItems: LazyPagingItems<Goods> = goodsState.items.collectAsLazyPagingItems()
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(AppTheme.customization.catalogStyle.columns),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium),
                contentPadding = PaddingValues(AppTheme.dimensions.medium)
            ) {
                items(lazyItems) { card ->
                    card?.let {
                        StoreItemCard(modifier = Modifier.align(Alignment.Center),
                            state = card,
                            onClick = { goodsInteractor.goodsDetails(card) },
                            onFavorite = { goodsInteractor.favorite(card.id, card.favorite) })
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
        GoodsScreen(
            goodsState = GoodsState(
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
            ),
            goodsInteractor = GoodsInteractor.Empty(),
            searchState = SearchState(),
            searchInteractor = SearchInteractor.Empty()
        )
    }
}