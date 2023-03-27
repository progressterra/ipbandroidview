package com.progressterra.ipbandroidview.widgets.proshkacatalogitems

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.proshkacatalogcard.ProshkaCatalogCardState

@Immutable
data class ProshkaCatalogItemsState(
    val items: List<ProshkaCatalogCardState> = emptyList()
)