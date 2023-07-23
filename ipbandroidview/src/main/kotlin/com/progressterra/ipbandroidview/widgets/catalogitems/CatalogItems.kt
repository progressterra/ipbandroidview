package com.progressterra.ipbandroidview.widgets.catalogitems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCard
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState

@Composable
fun CatalogItems(
    modifier: Modifier = Modifier, state: CatalogCardState, useComponent: UseCatalogItems
) {
    LazyVerticalGrid(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 40.dp)
    ) {
        items(state.children) { item ->
            Box(contentAlignment = Alignment.Center) {
                CatalogCard(
                    state = item, useComponent = useComponent
                )
            }
        }
    }
}

@Preview
@Composable
fun CatalogItemsPreview() {
    val mockChildren = listOf(
        CatalogCardState(
            id = "1", name = "Item 1", image = "https://example.com/image1.jpg"
        ), CatalogCardState(
            id = "2", name = "Item 2", image = "https://example.com/image2.jpg"
        ), CatalogCardState(
            id = "3", name = "Item 3", image = "https://example.com/image3.jpg"
        )
    )

    val mockState = CatalogCardState(
        id = "0",
        name = "Parent",
        image = "https://example.com/image0.jpg",
        children = mockChildren
    )
    Column(modifier = Modifier.fillMaxWidth()) {
        CatalogItems(
            state = mockState, useComponent = UseCatalogItems.Empty()
        )
    }
}
