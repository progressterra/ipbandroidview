package com.progressterra.ipbandroidview.widgets.proshkastoreitems

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCardState

@Immutable
data class ProshkaStoreItemsState(
    val items: List<ProshkaStoreCardState> = emptyList()
) {

    fun updateItems(newItems: List<ProshkaStoreCardState>) =
        copy(items = newItems)
}