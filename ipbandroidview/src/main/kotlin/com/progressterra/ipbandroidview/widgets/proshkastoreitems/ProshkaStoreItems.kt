package com.progressterra.ipbandroidview.widgets.proshkastoreitems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCard

@Composable
fun ProshkaStoreItems(
    modifier: Modifier = Modifier,
    state: ProshkaStoreItemsState,
    useComponent: UseProshkaStoreItems
) {
    LazyVerticalGrid(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        items(state.items) { item ->
            ProshkaStoreCard(
                state = item,
                useComponent = useComponent
            )
        }
    }
}