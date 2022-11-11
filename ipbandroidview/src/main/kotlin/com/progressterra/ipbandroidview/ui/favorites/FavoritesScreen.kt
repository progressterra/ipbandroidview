package com.progressterra.ipbandroidview.ui.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.StateBox
import com.progressterra.ipbandroidview.components.StoreCard
import com.progressterra.ipbandroidview.components.ThemedLayout
import com.progressterra.ipbandroidview.components.topbar.ThemedTopAppBar
import com.progressterra.ipbandroidview.model.Goods
import com.progressterra.ipbandroidview.model.GoodsColor
import com.progressterra.ipbandroidview.model.GoodsSize
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun FavoritesScreen(
    state: FavoritesState, interactor: FavoritesInteractor
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.favorites))
    }) { _, _ ->
        StateBox(
            state = state,
            onRefresh = { interactor.refresh() }) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(AppTheme.customization.catalogStyle.columns),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium),
                contentPadding = PaddingValues(AppTheme.dimensions.medium)
            ) {
                items(state.items) { goods ->
                    StoreCard(modifier = Modifier.align(Alignment.Center),
                        state = goods,
                        onClick = { interactor.goodsDetails(goods) },
                        onFavorite = {
                            interactor.favoriteSpecific(goods)
                        })
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