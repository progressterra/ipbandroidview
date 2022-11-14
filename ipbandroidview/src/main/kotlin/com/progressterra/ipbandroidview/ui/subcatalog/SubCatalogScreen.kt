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
import com.progressterra.ipbandroidview.model.StoreGoods
import com.progressterra.ipbandroidview.model.SubCategory
import com.progressterra.ipbandroidview.theme.AppTheme
import com.progressterra.ipbandroidview.ui.search.SearchState

@Composable
fun SubCatalogScreen(
    subCatalogState: () -> SubCatalogState,
    searchState: () -> SearchState,
    back: () -> Unit,
    subCategory: (SubCategory) -> Unit,
    filters: () -> Unit,
    favoriteSpecific: (StoreGoods) -> Unit,
    searchRefresh: () -> Unit,
    openDetails: (StoreGoods) -> Unit,
    keyword: (String) -> Unit,
    search: () -> Unit
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
                contentPadding = PaddingValues(AppTheme.dimensions.small)
            ) {
                items(subCatalogState().currentCategory?.subCategories ?: emptyList()) {
                    SubCategory(state = { it }, onClick = { subCategory(it) })
                }
            }
        }
    }
}

@Preview
@Composable
private fun SubCatalogScreenPreview() {
    AppTheme {
    }
}