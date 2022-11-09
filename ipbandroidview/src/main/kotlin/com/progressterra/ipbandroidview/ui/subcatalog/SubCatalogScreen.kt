package com.progressterra.ipbandroidview.ui.subcatalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.components.SearchBox
import com.progressterra.ipbandroidview.components.SubCategory
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
    SearchBox(state = searchState,
        onRefresh = { searchInteractor.refresh() },
        onFavorite = { id, favorite -> searchInteractor.favorite(id, favorite) },
        onGoods = { searchInteractor.goodsDetails(it) }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.background),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(subCatalogState.currentCategory?.subCategories ?: emptyList()) {
                SubCategory(state = it, onClick = { subCatalogInteractor.subCategory(it) })
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