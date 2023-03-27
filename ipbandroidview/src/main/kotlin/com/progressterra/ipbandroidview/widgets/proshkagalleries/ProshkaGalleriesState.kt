package com.progressterra.ipbandroidview.widgets.proshkagalleries

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCardState

@Immutable
data class ProshkaGalleriesState(
    val items: List<ProshkaStoreCardState> = emptyList()
)