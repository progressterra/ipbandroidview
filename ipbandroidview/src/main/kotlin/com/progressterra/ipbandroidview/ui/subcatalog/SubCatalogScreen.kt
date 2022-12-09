package com.progressterra.ipbandroidview.ui.subcatalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.composable.CategorySearchBar
import com.progressterra.ipbandroidview.composable.SubCategory
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.model.store.Category
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun SubCatalogScreen(
    state: () -> SubCatalogState,
    back: () -> Unit,
    subCategory: (Category) -> Unit,
    keyword: (String) -> Unit,
    search: () -> Unit,
    onExpand: () -> Unit,
    onClear: () -> Unit
) {
    ThemedLayout(topBar = {
        CategorySearchBar(
            category = state().currentCategory::name,
            state = state,
            onBack = back,
            onKeyword = keyword,
            onSearch = search,
            onClear = onClear,
            onExpand = onExpand
        )
    }) { _, _ ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
            contentPadding = PaddingValues(AppTheme.dimensions.small)
        ) {
            items(state().currentCategory.subCategories) {
                SubCategory(state = { it }, openCategory = subCategory)
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