package com.progressterra.ipbandroidview.widgets.catalogitems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCard
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState

@Composable
fun CatalogItems(
    modifier: Modifier = Modifier,
    state: CatalogCardState,
    useComponent: UseCatalogItems
) {
    LazyVerticalGrid(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 40.dp)
    ) {
        items(state.children) { item ->
            CatalogCard(
                state = item,
                useComponent = useComponent
            )
        }
    }
}