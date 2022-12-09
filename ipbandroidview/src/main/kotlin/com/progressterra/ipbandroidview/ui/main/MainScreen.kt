package com.progressterra.ipbandroidview.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.component.Notifications
import com.progressterra.ipbandroidview.composable.component.StoreCard
import com.progressterra.ipbandroidview.composable.component.ThemedLayout
import com.progressterra.ipbandroidview.composable.component.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.element.StateBox
import com.progressterra.ipbandroidview.composable.utils.items
import com.progressterra.ipbandroidview.model.store.StoreGoods
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun MainScreen(
    state: () -> MainState,
    favoriteSpecific: (StoreGoods) -> Unit,
    openDetails: (StoreGoods) -> Unit,
    refresh: () -> Unit
) {
    ThemedLayout(topBar = {
        Column(
            modifier = Modifier.background(AppTheme.colors.surfaces),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiny)
        ) {
            if (state().userExist)
                ThemedTopAppBar(title = stringResource(R.string.main))
        }
    }) { _, _ ->
        StateBox(
            state = state()::screenState, refresh = refresh
        ) {
            Column(Modifier.padding(top = AppTheme.dimensions.small)) {
                Notifications(
                    state = state
                )
                val lazyItems: LazyPagingItems<StoreGoods> =
                    state().items.collectAsLazyPagingItems()
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(AppTheme.customization.catalogStyle.columns),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
                    contentPadding = PaddingValues(AppTheme.dimensions.small)
                ) {
                    items(lazyItems) { goods ->
                        goods?.let {
                            StoreCard(
                                state = { goods },
                                onClick = { openDetails(goods) },
                                onFavorite = { favoriteSpecific(goods) })
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    AppTheme {}
}