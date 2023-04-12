package com.progressterra.ipbandroidview.widgets.catalogitems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.proshkacatalogcard.ProshkaCatalogCard

@Composable
fun CatalogItems(
    modifier: Modifier = Modifier,
    state: CatalogItemsState,
    useComponent: UseCatalogItems
) {
    LazyVerticalGrid(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        items(state.items) { item ->
            ProshkaCatalogCard(
                state = item,
                useComponent = useComponent
            )
        }
    }
}