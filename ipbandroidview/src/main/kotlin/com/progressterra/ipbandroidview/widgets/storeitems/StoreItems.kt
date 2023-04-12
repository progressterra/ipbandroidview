package com.progressterra.ipbandroidview.widgets.storeitems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.storecard.StoreCard

@Composable
fun StoreItems(
    modifier: Modifier = Modifier,
    state: StoreItemsState,
    useComponent: UseStoreItems
) {
    LazyVerticalGrid(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        items(state.items) { item ->
            StoreCard(
                state = item,
                useComponent = useComponent
            )
        }
    }
}