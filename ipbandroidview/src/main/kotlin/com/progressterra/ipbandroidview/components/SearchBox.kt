package com.progressterra.ipbandroidview.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.StoreGoods
import com.progressterra.ipbandroidview.model.component.Keyword
import com.progressterra.ipbandroidview.model.component.SearchGoods
import com.progressterra.ipbandroidview.model.component.Visible
import com.progressterra.ipbandroidview.theme.AppTheme

interface SearchBoxState : SearchGoods, Keyword, Visible {

    val searchScreenState: ScreenState
}

@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    state: () -> SearchBoxState,
    onRefresh: () -> Unit,
    onFavorite: (StoreGoods) -> Unit,
    onGoods: (StoreGoods) -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = modifier) {
        AnimatedVisibility(
            visible = state().visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            StateBox(
                state = state()::searchScreenState,
                onRefresh = onRefresh
            ) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxSize(),
                    columns = GridCells.Fixed(AppTheme.customization.catalogStyle.columns),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
                    contentPadding = PaddingValues(AppTheme.dimensions.small)
                ) {
                    items(state().searchGoods) { goods ->
                        StoreCard(modifier = Modifier.align(Alignment.Center),
                            state = { goods },
                            onClick = { onGoods(goods) },
                            onFavorite = { onFavorite(goods) })
                    }
                }
            }
        }
        AnimatedVisibility(
            visible = !state().visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            content()
        }
    }
}