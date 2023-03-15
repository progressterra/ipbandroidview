package com.progressterra.ipbandroidview.ui.subcatalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.composable.component.SubCategoryComponent
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.theme.IpbTheme

@Composable
fun SubCatalogScreen(
    state: SubCatalogState,
//    interactor: SubCatalogInteractor
) {
    ThemedLayout(topBar = {
//        CategoryBar(
//            category = state.currentCategoryWithSubcategories.name,
//            state = state,
//            onBack = { interactor.onBack() },
//            onKeyword = { interactor.editKeyword(it) },
//            onSearch = { interactor.search() },
//            onClear = { interactor.clearSearch() },
//            onExpand = { interactor.expandSearch() }
//        )
    }) { _, _ ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(state.currentCategoryWithSubcategories.subCategories) { category ->
                SubCategoryComponent(state = category, openCategory = {
//                    interactor.onSubCategory(it)
                })
            }
        }
    }
}

@Preview
@Composable
private fun SubCatalogScreenPreview() {
    IpbTheme {
    }
}