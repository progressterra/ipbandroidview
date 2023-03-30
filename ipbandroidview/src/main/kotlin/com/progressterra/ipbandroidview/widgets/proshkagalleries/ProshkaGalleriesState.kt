package com.progressterra.ipbandroidview.widgets.proshkagalleries

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCardState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Immutable
data class ProshkaGalleriesState(
    val items: Flow<PagingData<ProshkaStoreCardState>> = emptyFlow()
) {

    fun updateItems(items: Flow<PagingData<ProshkaStoreCardState>>) = copy(items = items)
}