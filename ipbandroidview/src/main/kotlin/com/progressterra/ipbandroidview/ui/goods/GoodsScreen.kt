package com.progressterra.ipbandroidview.ui.goods

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.progressterra.ipbandroidview.composable.GoodsSearchBar
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.composable.StoreCard
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.utils.items
import com.progressterra.ipbandroidview.model.store.StoreGoods
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun GoodsScreen(
    state: GoodsState,
    interactor: GoodsInteractor
) {
    ThemedLayout(topBar = {
        GoodsSearchBar(
            state = state,
            onBack = interactor::onBack,
            onKeyword = interactor::editKeyword,
            onSearch = interactor::search,
            onFilters = interactor::filters,
            onClear = interactor::clear
        )
    }) { _, _ ->
        StateBox(
            state = state.screenState,
            refresh = interactor::refresh
        ) {
            val lazyItems: LazyPagingItems<StoreGoods> =
                state.itemsFlow.collectAsLazyPagingItems()
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(AppTheme.customization.catalogStyle.columns),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
                contentPadding = PaddingValues(AppTheme.dimensions.small)
            ) {
                if (lazyItems.itemCount > 0)
                    items(lazyItems) { goods ->
                        goods?.let {
                            StoreCard(
                                state = goods,
                                onClick = { interactor.openDetails(goods) },
                                onFavorite = { interactor.favoriteSpecific(goods) })
                        }
                    }
                if (state.items.isNotEmpty())
                    items(state.items) { goods ->
                        StoreCard(
                            state = goods,
                            onClick = { interactor.openDetails(goods) },
                            onFavorite = { interactor.favoriteSpecific(goods) })
                    }
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    AppTheme {

    }
}