package com.progressterra.ipbandroidview.widgets.proshkacartitems

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.proshkacartcard.ProshkaCartCardState

@Immutable
data class ProshkaCartItemsState(
    val items: List<ProshkaCartCardState> = emptyList()
)