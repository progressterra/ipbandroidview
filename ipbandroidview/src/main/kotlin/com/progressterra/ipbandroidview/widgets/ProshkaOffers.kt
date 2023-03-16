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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.ProshkaOffer
import com.progressterra.ipbandroidview.features.ProshkaOfferEvent
import com.progressterra.ipbandroidview.features.ProshkaOfferState
import com.progressterra.ipbandroidview.features.UseProshkaOffer
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText


@Immutable
data class ProshkaOffersState(
    val items: List<ProshkaOfferState> = emptyList()
)

interface UseProshkaOffers : UseProshkaOffer {

    class Empty : UseProshkaOffers {

        override fun handleEvent(id: String, event: ProshkaOfferEvent) = Unit
    }
}

@Composable
fun ProshkaOffers(
    modifier: Modifier = Modifier,
    state: ProshkaOffersState,
    useComponent: UseProshkaOffers
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BrushedText(
            modifier = Modifier.padding(start = 20.dp),
            text = stringResource(R.string.offers),
            style = IpbTheme.typography.title,
            tint = IpbTheme.colors.textPrimary1.asBrush(),
        )
        LazyRow(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            items(state.items) { item ->
                ProshkaOffer(
                    id = item.id,
                    state = item,
                    useComponent = useComponent
                )
            }
        }
    }
}