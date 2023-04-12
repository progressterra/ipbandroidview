package com.progressterra.ipbandroidview.widgets.galleries

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Immutable
data class GalleriesState(
    val items: Flow<PagingData<StoreCardState>> = emptyFlow()
) {

    fun updateItems(items: Flow<PagingData<StoreCardState>>) = copy(items = items)
}