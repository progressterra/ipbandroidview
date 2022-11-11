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
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.theme.AppTheme
import com.progressterra.ipbandroidview.ui.search.SearchInteractor
import com.progressterra.ipbandroidview.ui.search.SearchState

@Composable
fun CatalogScreen(
    catalogState: CatalogState,
    catalogInteractor: CatalogInteractor,
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
        SearchBox(
            state = searchState,
            onRefresh = { searchInteractor.refresh() },
            onFavorite = { searchInteractor.favorite(it) },
            onGoods = { searchInteractor.goodsDetails(it) }) {
            StateBox(
                state = catalogState.screenState,
                onRefresh = { catalogInteractor.refresh() }) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxSize(),
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium),
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium),
                    contentPadding = PaddingValues(AppTheme.dimensions.medium)
                ) {
                    items(catalogState.categories) {
                        Category(state = it, onClick = { catalogInteractor.openDetails(it) })
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
        CatalogScreen(
            catalogState = CatalogState(
                categories = emptyList(),
                screenState = ScreenState.SUCCESS
            ),
            catalogInteractor = CatalogInteractor.Empty(),
            searchState = SearchState(),
            searchInteractor = SearchInteractor.Empty()
        )
    }
}