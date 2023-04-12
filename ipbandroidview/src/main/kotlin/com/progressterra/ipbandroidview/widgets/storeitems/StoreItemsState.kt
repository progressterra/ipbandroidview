package com.progressterra.ipbandroidview.widgets.storeitems

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.storecard.StoreCardState

@Immutable
data class StoreItemsState(
    val items: List<StoreCardState> = emptyList()
) {

    fun updateItems(newItems: List<StoreCardState>) =
        copy(items = newItems)
}