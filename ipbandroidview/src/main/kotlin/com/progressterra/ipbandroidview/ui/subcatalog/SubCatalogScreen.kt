package com.progressterra.ipbandroidview.ui.subcatalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.components.SearchBox
import com.progressterra.ipbandroidview.components.SubCategory
import com.progressterra.ipbandroidview.components.ThemedLayout
import com.progressterra.ipbandroidview.components.topbar.SearchTopBar
import com.progressterra.ipbandroidview.theme.AppTheme
import com.progressterra.ipbandroidview.ui.search.SearchInteractor
import com.progressterra.ipbandroidview.ui.search.SearchState

@Composable
fun SubCatalogScreen(
    subCatalogState: SubCatalogState,
    subCatalogInteractor: SubCatalogInteractor,
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium),
                contentPadding = PaddingValues(AppTheme.dimensions.medium)
            ) {
                items(subCatalogState.currentCategory?.subCategories ?: emptyList()) {
                    SubCategory(state = it, onClick = { subCatalogInteractor.subCategory(it) })
                }
            }
        }
    }
}

@Preview
@Composable
private fun SubCatalogScreenPreview() {
    AppTheme {
        SubCatalogScreen(
            subCatalogState = SubCatalogState(
                currentCategory = null,
            ),
            subCatalogInteractor = SubCatalogInteractor.Empty(),
            searchState = SearchState(),
            searchInteractor = SearchInteractor.Empty()
        )
    }
}