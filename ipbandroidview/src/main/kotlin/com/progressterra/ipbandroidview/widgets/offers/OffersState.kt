package com.progressterra.ipbandroidview.widgets.offers

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.offer.OfferState

@Immutable
data class OffersState(
    val items: List<OfferState> = emptyList()
)