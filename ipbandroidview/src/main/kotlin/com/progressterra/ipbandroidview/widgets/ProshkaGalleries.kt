package com.progressterra.ipbandroidview.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCard
import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCardEvent
import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCardState
import com.progressterra.ipbandroidview.features.proshkastorecard.UseProshkaStoreCard
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.CounterEvent

@Immutable
data class ProshkaGalleriesState(
    val title: String = "",
    val items: List<ProshkaStoreCardState> = emptyList()
)

interface UseProshkaGalleries : UseProshkaStoreCard {

    class Empty : UseProshkaGalleries {

        override fun handleEvent(id: String, event: CounterEvent) = Unit

        override fun handleEvent(id: String, event: ProshkaStoreCardEvent) = Unit
    }
}

@Composable
fun ProshkaGalleries(
    modifier: Modifier = Modifier,
    state: ProshkaGalleriesState,
    useComponent: UseProshkaGalleries
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        BrushedText(
            modifier = Modifier.padding(start = 20.dp),
            text = state.title,
            style = IpbTheme.typography.title,
            tint = IpbTheme.colors.textPrimary1.asBrush(),
        )
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
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
}