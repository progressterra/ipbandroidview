package com.progressterra.ipbandroidview.widgets.catalogitems

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.proshkacatalogcard.ProshkaCatalogCardState

@Immutable
data class CatalogItemsState(
    val items: List<ProshkaCatalogCardState> = emptyList()
) {

    fun updateItems(newItems: List<ProshkaCatalogCardState>) =
        copy(items = newItems)
}