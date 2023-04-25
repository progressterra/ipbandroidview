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

        fun uItems(newItems: Flow<PagingData<StoreCardState>>) = copy(items = newItems)
    }

    @Immutable
    data class Listed(
        val items: List<StoreCardState> = emptyList()
    ) : StoreItemsState() {

        fun uItems(newItems: List<StoreCardState>) = copy(items = newItems)
    }
}
