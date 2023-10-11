package com.progressterra.ipbandroidview.widgets.galleries

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.progressterra.ipbandroidview.features.storecard.StoreCard
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn

@Composable
fun Galleries(
    modifier: Modifier = Modifier,
    state: GalleriesState,
    useComponent: UseGalleries
) {
    val items = state.items.collectAsLazyPagingItems()
    StateColumn(
        modifier = modifier.fillMaxWidth(),
        state = state.state,
        useComponent = useComponent,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        BrushedText(
            modifier = Modifier.padding(start = 20.dp),
            text = state.title,
            style = IpbTheme.typography.title,
            tint = IpbTheme.colors.textPrimary.asBrush(),
        )
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            items(count = items.itemCount,
                key = items.itemKey { it.id }
            ) {
                items[it]?.let { item ->
                    Box(contentAlignment = Alignment.Center) {
                        StoreCard(
                            state = item,
                            useComponent = useComponent
                        )
                    }
                }
            }
        }
    }
}