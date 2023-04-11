package com.progressterra.ipbandroidview.widgets.proshkagalleries

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCard
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun ProshkaGalleries(
    modifier: Modifier = Modifier,
    state: ProshkaGalleriesState,
    title: String,
    useComponent: UseProshkaGalleries
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        BrushedText(
            modifier = Modifier.padding(start = 20.dp),
            text = title,
            style = IpbTheme.typography.title,
            tint = IpbTheme.colors.textPrimary.asBrush(),
        )
        val items = state.items.collectAsLazyPagingItems()
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            items(items) { item ->
                item?.let {
                    ProshkaStoreCard(
                        state = it,
                        useComponent = useComponent
                    )
                }
            }
        }
    }
}