package com.progressterra.ipbandroidview.widgets.catalogitems

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState

@Immutable
data class CatalogItemsState(
    val items: List<CatalogCardState> = emptyList()
) {

    fun updateItems(newItems: List<CatalogCardState>) =
        copy(items = newItems)
}