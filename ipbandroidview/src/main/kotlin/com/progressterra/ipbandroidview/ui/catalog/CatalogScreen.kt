package com.progressterra.ipbandroidview.ui.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.progressterra.ipbandroidview.components.Category
import com.progressterra.ipbandroidview.components.SearchBox
import com.progressterra.ipbandroidview.components.StateBox
import com.progressterra.ipbandroidview.components.StoreItemCard
import com.progressterra.ipbandroidview.components.utils.items
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.dto.Goods
import com.progressterra.ipbandroidview.theme.AppTheme
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun CatalogScreen(state: CatalogState, interactor: CatalogInteractor) {
    SearchBox(state = state,
        onRefresh = { interactor.refresh() },
        onFavorite = { interactor.favorite(it) },
        onGoods = { interactor.openDetails(it) }) {
        StateBox(state = state.screenState, onRefresh = { interactor.refresh() }) {
            if (state.categories.isNotEmpty())
            val lazyItems: LazyPagingItems<Goods> = state.items.collectAsLazyPagingItems()
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.colors.background),
                columns = GridCells.Fixed(AppTheme.customization.catalogStyle.columns),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(lazyItems, key = { item -> item.id }) { card ->
                    Box(modifier = Modifier.fillMaxSize()) {
                        card?.let {
                            StoreItemCard(modifier = Modifier.align(Alignment.Center),
                                state = card,
                                onClick = { interactor.card(card.id) },
                                onFavorite = { interactor.favorite(card.id) })
                        }
                    }
                }
            }
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.colors.background),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.categories) {
                    Category(state = it, onClick = { interactor.openDetails(it.id) })
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
                searchGoods = emptyFlow(),
                screenState = ScreenState.SUCCESS
            ),
            interactor = CatalogInteractor.Empty()
        )
    }
}