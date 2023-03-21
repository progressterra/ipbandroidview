package com.progressterra.ipbandroidview.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.ProshkaCatalogCard
import com.progressterra.ipbandroidview.features.ProshkaCatalogCardEvent
import com.progressterra.ipbandroidview.features.ProshkaCatalogCardState
import com.progressterra.ipbandroidview.features.UseProshkaCatalogCard


@Immutable
data class ProshkaCatalogItemsState(
    val items: List<ProshkaCatalogCardState> = emptyList()
)

interface UseProshkaCatalogItems : UseProshkaCatalogCard {

    class Empty : UseProshkaCatalogItems {

        override fun handleEvent(id: String, event: ProshkaCatalogCardEvent) = Unit
    }
}

@Composable
fun ProshkaCatalogItems(
    modifier: Modifier = Modifier,
    state: ProshkaCatalogItemsState,
    useComponent: UseProshkaCatalogItems
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