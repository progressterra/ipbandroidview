package com.progressterra.ipbandroidview.widgets.proshkaoffers

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.proshkaoffer.ProshkaOfferState

@Immutable
data class ProshkaOffersState(
    val items: List<ProshkaOfferState> = emptyList()
)