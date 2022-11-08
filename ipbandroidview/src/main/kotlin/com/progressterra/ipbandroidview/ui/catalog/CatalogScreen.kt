package com.progressterra.ipbandroidview.ui.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.components.Category
import com.progressterra.ipbandroidview.components.SearchBox
import com.progressterra.ipbandroidview.components.StateBox
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun CatalogScreen(state: CatalogState, interactor: CatalogInteractor) {
    SearchBox(state = state,
        onRefresh = { interactor.refresh() },
        onFavorite = { id, favorite -> interactor.favorite(id, favorite) },
        onGoods = { interactor.goodsDetails(it) }) {
        StateBox(state = state.screenState, onRefresh = { interactor.refresh() }) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.colors.background),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.categories) {
                    Category(state = it, onClick = { interactor.category(it.id) })
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
            state = CatalogState(
                categories = emptyList(),
                keyword = "",
                searchGoods = emptyList(),
                screenState = ScreenState.SUCCESS
            ),
            interactor = CatalogInteractor.Empty()
        )
    }
}