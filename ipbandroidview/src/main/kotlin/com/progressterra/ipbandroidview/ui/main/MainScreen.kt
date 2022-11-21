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
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.progressterra.ipbandroidview.composable.component.CategorySearchBar
import com.progressterra.ipbandroidview.composable.component.SearchBox
import com.progressterra.ipbandroidview.composable.component.StoreCard
import com.progressterra.ipbandroidview.composable.component.ThemedLayout
import com.progressterra.ipbandroidview.composable.element.StateBox
import com.progressterra.ipbandroidview.composable.utils.items
import com.progressterra.ipbandroidview.model.StoreGoods
import com.progressterra.ipbandroidview.theme.AppTheme
import com.progressterra.ipbandroidview.ui.search.SearchState

@Composable
fun MainScreen(
    mainState: () -> MainState,
    searchState: () -> SearchState,
    search: () -> Unit,
    keyword: (String) -> Unit,
    back: () -> Unit,
    favoriteSpecific: (StoreGoods) -> Unit,
    searchRefresh: () -> Unit,
    openDetails: (StoreGoods) -> Unit,
    filters: () -> Unit,
    refresh: () -> Unit,
    clear: () -> Unit
) {
    ThemedLayout(topBar = {
        CategorySearchBar(
            state = searchState,
            onBack = back,
            onKeyword = keyword,
            onSearch = search,
            onFilters = filters,
            onClear = clear
        )
    }) { _, _ ->
        SearchBox(
            state = searchState,
            onRefresh = searchRefresh,
            onFavorite = favoriteSpecific,
            onGoods = openDetails
        ) {
            StateBox(
                state = mainState()::screenState, refresh = refresh
            ) {
                val lazyItems: LazyPagingItems<StoreGoods> =
                    mainState().items.collectAsLazyPagingItems()
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(AppTheme.customization.catalogStyle.columns),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
                    contentPadding = PaddingValues(AppTheme.dimensions.small)
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
    AppTheme {}
}