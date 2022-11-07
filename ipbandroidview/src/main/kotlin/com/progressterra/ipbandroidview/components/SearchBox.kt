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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.progressterra.ipbandroidview.actions.Keyword
import com.progressterra.ipbandroidview.components.utils.items
import com.progressterra.ipbandroidview.dto.Goods
import com.progressterra.ipbandroidview.dto.component.Screen
import com.progressterra.ipbandroidview.dto.component.SearchGoodsFlow
import com.progressterra.ipbandroidview.theme.AppTheme

interface SearchBoxState : Screen, SearchGoodsFlow, Keyword

@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    state: SearchBoxState,
    onRefresh: () -> Unit,
    onFavorite: (String) -> Unit,
    onGoods: (String) -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = state.keyword.isNotBlank(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            StateBox(
                modifier = Modifier
                    .fillMaxSize(),
                state = state.screenState,
                onRefresh = onRefresh
            ) {
                val searchItems: LazyPagingItems<Goods> =
                    state.searchGoods.collectAsLazyPagingItems()
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(AppTheme.colors.background),
                    columns = GridCells.Fixed(AppTheme.customization.catalogStyle.columns),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(searchItems, key = { item -> item.id }) { card ->
                        Box(modifier = Modifier.fillMaxSize()) {
                            card?.let {
                                StoreItemCard(modifier = Modifier.align(Alignment.Center),
                                    state = card,
                                    onClick = { onGoods(card.id) },
                                    onFavorite = { onFavorite(card.id) })
                            }
                        }
                    }
                }
            }
        }
        content()
    }
}