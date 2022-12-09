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
import com.progressterra.ipbandroidview.composable.CatalogSearchBar
import com.progressterra.ipbandroidview.composable.MainCategoryItem
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.model.store.Category
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun CatalogScreen(
    state: () -> CatalogState,
    refresh: () -> Unit,
    openCategory: (Category) -> Unit,
    search: () -> Unit,
    keyword: (String) -> Unit,
    onClear: () -> Unit
) {
    ThemedLayout(topBar = {
        CatalogSearchBar(
            state = state,
            onKeyword = keyword,
            onSearch = search,
            onClear = onClear
        )
    }) { _, _ ->
        StateBox(
            state = state()::screenState, refresh = refresh
        ) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
                contentPadding = PaddingValues(AppTheme.dimensions.small)
            ) {
                items(state().categories) {
                    MainCategoryItem(state = { it }, openCategory = { openCategory(it) })
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