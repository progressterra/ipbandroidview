package com.progressterra.ipbandroidview.ui.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.StateBox
import com.progressterra.ipbandroidview.components.StoreItemCard
import com.progressterra.ipbandroidview.components.topbar.ThemedTopAppBar
import com.progressterra.ipbandroidview.dto.Goods
import com.progressterra.ipbandroidview.dto.GoodsColor
import com.progressterra.ipbandroidview.dto.size.GoodsSize
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun FavoritesScreen(
    state: FavoritesState, interactor: FavoritesInteractor
) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.favorites))
    }) { padding ->
        StateBox(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(AppTheme.colors.background)
            .padding(start = 8.dp, top = 8.dp, end = 8.dp),
            state = state.screenState,
            onRefresh = { interactor.refresh() }) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(AppTheme.customization.catalogStyle.columns),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.items) { card ->
                    Box(modifier = Modifier.fillMaxSize()) {
                        StoreItemCard(modifier = Modifier.align(Alignment.Center),
                            state = card,
                            onClick = { interactor.goodsDetails(card) },
                            onFavorite = {
                                interactor.favorite(
                                    card.id, card.favorite
                                )
                            })
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
        FavoritesScreen(
            state = FavoritesState(
                items = listOf(
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
            ), interactor = FavoritesInteractor.Empty()
        )
    }
}