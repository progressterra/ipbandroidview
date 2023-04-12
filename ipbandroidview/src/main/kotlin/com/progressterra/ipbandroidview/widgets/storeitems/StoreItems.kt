package com.progressterra.ipbandroidview.widgets.storeitems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.progressterra.ipbandroidview.features.storecard.StoreCard
import com.progressterra.ipbandroidview.shared.ui.items

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
                    contentPadding = PaddingValues(horizontal = 20.dp)
                ) {
                    items(lazyItems) { item ->
                        item?.let {
                            StoreCard(
                                state = it, useComponent = useComponent
                            )
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
                    contentPadding = PaddingValues(horizontal = 20.dp)
                ) {
                    items(state.items) {
                        StoreCard(
                            state = it, useComponent = useComponent
                        )
                    }
                }
            }
        }
    }

}