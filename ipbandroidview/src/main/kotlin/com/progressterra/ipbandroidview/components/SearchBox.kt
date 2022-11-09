package com.progressterra.ipbandroidview.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.actions.Keyword
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.dto.Goods
import com.progressterra.ipbandroidview.dto.component.SearchGoods
import com.progressterra.ipbandroidview.dto.component.Visible
import com.progressterra.ipbandroidview.theme.AppTheme

interface SearchBoxState : SearchGoods, Keyword, Visible {

    val searchScreenState: ScreenState
}

@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    state: SearchBoxState,
    onRefresh: () -> Unit,
    onFavorite: (String, Boolean) -> Unit,
    onGoods: (Goods) -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = state.visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            StateBox(
                modifier = Modifier
                    .fillMaxSize(),
                state = state.searchScreenState,
                onRefresh = onRefresh
            ) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(AppTheme.colors.background),
                    columns = GridCells.Fixed(AppTheme.customization.catalogStyle.columns),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.searchGoods) { card ->
                        Box(modifier = Modifier.fillMaxSize()) {
                            StoreItemCard(modifier = Modifier.align(Alignment.Center),
                                state = card,
                                onClick = { onGoods(card) },
                                onFavorite = { onFavorite(card.id, card.favorite) })
                        }
                    }
                }
            }
        }
        AnimatedVisibility(
            visible = !state.visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            content()
        }
    }
}