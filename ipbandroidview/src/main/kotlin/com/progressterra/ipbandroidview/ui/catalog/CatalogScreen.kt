package com.progressterra.ipbandroidview.ui.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.components.Category
import com.progressterra.ipbandroidview.components.SearchBox
import com.progressterra.ipbandroidview.components.StateBox
import com.progressterra.ipbandroidview.components.ThemedLayout
import com.progressterra.ipbandroidview.components.topbar.SearchTopBar
import com.progressterra.ipbandroidview.model.Category
import com.progressterra.ipbandroidview.model.StoreGoods
import com.progressterra.ipbandroidview.theme.AppTheme
import com.progressterra.ipbandroidview.ui.search.SearchState

@Composable
fun CatalogScreen(
    catalogState: () -> CatalogState,
    refresh: () -> Unit,
    openCategory: (Category) -> Unit,
    searchState: () -> SearchState,
    back: () -> Unit,
    openSearchGoods: (StoreGoods) -> Unit,
    favoriteSpecific: (StoreGoods) -> Unit,
    refreshSearch: () -> Unit,
    search: () -> Unit,
    keyword: (String) -> Unit,
    filters: () -> Unit
) {
    ThemedLayout(topBar = {
        SearchTopBar(state = searchState,
            onBack = back,
            onKeyword = keyword,
            onSearch = search,
            onFilters = filters)
    }) { _, _ ->
        SearchBox(
            state = searchState,
            onRefresh = refreshSearch,
            onFavorite = favoriteSpecific,
            onGoods = openSearchGoods
        ) {
            StateBox(
                state = catalogState()::screenState, onRefresh = refresh
            ) {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium),
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium),
                    contentPadding = PaddingValues(AppTheme.dimensions.medium)
                ) {
                    items(catalogState().categories) {
                        Category(state = { it }, onClick = { openCategory(it) })
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CatalogScreenPreview() {
    AppTheme {
    }
}