package com.progressterra.ipbandroidview.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.progressterra.ipbandroidview.components.StateBox
import com.progressterra.ipbandroidview.components.StoreItemCard
import com.progressterra.ipbandroidview.components.topbar.SearchTopBar
import com.progressterra.ipbandroidview.components.utils.items
import com.progressterra.ipbandroidview.dto.Goods
import com.progressterra.ipbandroidview.dto.GoodsColor
import com.progressterra.ipbandroidview.dto.size.GoodsSize
import com.progressterra.ipbandroidview.theme.AppTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun MainScreen(state: MainState, interactor: MainInteractor) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        SearchTopBar(state = state.searchBarState,
            onBack = { interactor.back() },
            onKeyword = { interactor.keyword(it) },
            onSearch = { interactor.search() },
            onFilters = {})
    }) { padding ->
        StateBox(modifier = Modifier
            .fillMaxSize()
            .padding(padding),
            state = state.screenState,
            onRefresh = { interactor.refresh() }) {
            val lazyItems: LazyPagingItems<Goods> = state.items.collectAsLazyPagingItems()
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.colors.background),
                columns = GridCells.Fixed(AppTheme.customization.catalogStyle.columns),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(lazyItems, key = { item -> item.id }) { card ->
                    Box(modifier = Modifier.fillMaxSize()) {
                        card?.let {
                            StoreItemCard(modifier = Modifier.align(Alignment.Center),
                                state = card,
                                onClick = { interactor.card(card.id) },
                                onFavorite = { interactor.favorite(card.id) })
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
            state = MainState(
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
                                countInCart = 0,
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
                                countInCart = 0,
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
                                countInCart = 0,
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
                                countInCart = 0,
                                image = "",
                                color = GoodsColor("", ""),
                                sizes = emptyList(),
                                size = GoodsSize(false, "", ""),
                                colors = emptyList()
                            )
                        )
                    )
                )
            ), interactor = MainInteractor.Empty()
        )
    }
}