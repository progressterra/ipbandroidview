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
import com.progressterra.ipbandroidview.components.StateBox
import com.progressterra.ipbandroidview.components.SubCategory
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.dto.SubCategory
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun SubCatalogScreen(state: SubCatalogState, interactor: SubCatalogInteractor) {
    SearchBox(state = state,
        onRefresh = { interactor.refresh() },
        onFavorite = { id, favorite -> interactor.favorite(id, favorite) },
        onGoods = { interactor.goodsDetails(it) }) {
        StateBox(state = state.screenState, onRefresh = { interactor.refresh() }) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.colors.background),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.currentCategory.subCategories) {
                    SubCategory(state = it, onClick = { interactor.subCategory(it.id) })
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
            state = SubCatalogState(
                searchGoods = emptyList(),
                keyword = "",
                screenState = ScreenState.SUCCESS,
                currentCategory = SubCategory(
                    id = "",
                    name = "",
                    subCategories = emptyList(),
                    hasNext = false
                ),
                filters = emptyList()
            ),
            interactor = SubCatalogInteractor.Empty()
        )
    }
}