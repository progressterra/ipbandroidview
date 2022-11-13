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
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.progressterra.ipbandroidview.components.SearchBox
import com.progressterra.ipbandroidview.components.StateBox
import com.progressterra.ipbandroidview.components.StoreCard
import com.progressterra.ipbandroidview.components.ThemedLayout
import com.progressterra.ipbandroidview.components.topbar.SearchTopBar
import com.progressterra.ipbandroidview.components.utils.items
import com.progressterra.ipbandroidview.model.StoreGoods
import com.progressterra.ipbandroidview.theme.AppTheme
import com.progressterra.ipbandroidview.ui.search.SearchState

@Composable
fun GoodsScreen(
    goodsState: () -> GoodsState,
    searchState: () -> SearchState,
    searchRefresh: () -> Unit,
    search: () -> Unit,
    keyword: (String) -> Unit,
    back: () -> Unit,
    favoriteSpecific: (StoreGoods) -> Unit,
    refresh: () -> Unit,
    openDetails: (StoreGoods) -> Unit,
    filters: () -> Unit
) {
    ThemedLayout(topBar = {
        SearchTopBar(
            state = searchState,
            onBack = back,
            onKeyword = keyword,
            onSearch = search,
            onFilters = filters
        )
    }) { _, _ ->
        SearchBox(
            state = searchState,
            onRefresh = searchRefresh,
            onFavorite = favoriteSpecific,
            onGoods = openDetails
        ) {
            StateBox(
                state = goodsState()::screenState,
                onRefresh = refresh
            ) {
                val lazyItems: LazyPagingItems<StoreGoods> =
                    goodsState().items.collectAsLazyPagingItems()
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(AppTheme.customization.catalogStyle.columns),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium),
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium),
                    contentPadding = PaddingValues(AppTheme.dimensions.medium)
                ) {
                    items(lazyItems) { goods ->
                        goods?.let {
                            StoreCard(modifier = Modifier.align(Alignment.Center),
                                state = { goods },
                                onClick = { openDetails(goods) },
                                onFavorite = { favoriteSpecific(goods) })
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

    }
}