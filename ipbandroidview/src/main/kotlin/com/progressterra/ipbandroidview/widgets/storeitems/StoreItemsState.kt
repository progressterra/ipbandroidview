package com.progressterra.ipbandroidview.widgets.storeitems

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

sealed class StoreItemsState {

    data class Flowed(
        val items: Flow<PagingData<StoreCardState>> = emptyFlow()
    ) : StoreItemsState() {

        fun updateItems(newItems: Flow<PagingData<StoreCardState>>) = copy(items = newItems)
    }

    @Immutable
    data class Listed(
        val items: List<StoreCardState> = emptyList()
    ) : StoreItemsState() {

        fun updateItems(newItems: List<StoreCardState>) = copy(items = newItems)
    }
}
