package com.progressterra.ipbandroidview.widgets.storeitems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.progressterra.ipbandroidview.features.storecard.StoreCard

@Composable
fun StoreItems(
    modifier: Modifier = Modifier, state: StoreItemsState, useComponent: UseStoreItems
) {
    when (state) {
        is StoreItemsState.Flowed -> {
            val lazyItems = state.items.collectAsLazyPagingItems()
            if (lazyItems.itemCount > 0) {
                LazyVerticalGrid(
                    modifier = modifier,
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalArrangement = Arrangement.spacedBy(30.dp),
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 40.dp)
                ) {
                    items(
                        count = lazyItems.itemCount,
                        key = lazyItems.itemKey { it.id }
                    ) { index ->
                        lazyItems[index]?.let {
                            Box(contentAlignment = Alignment.Center) {
                                StoreCard(
                                    state = it, useComponent = useComponent
                                )
                            }
                        }
                    }
                }
            }
        }

        is StoreItemsState.Listed -> {
            if (state.items.isNotEmpty()) {
                LazyVerticalGrid(
                    modifier = modifier,
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalArrangement = Arrangement.spacedBy(30.dp),
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 40.dp)
                ) {
                    items(state.items) {
                        Box(contentAlignment = Alignment.Center) {
                            StoreCard(
                                state = it, useComponent = useComponent
                            )
                        }
                    }
                }
            }
        }
    }

}