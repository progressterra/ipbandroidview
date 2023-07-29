package com.progressterra.ipbandroidview.widgets.storeitems

import androidx.paging.PagingData
import arrow.optics.optics
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@optics
data class StoreItemsState(
    val items: Flow<PagingData<StoreCardState>> = emptyFlow()
) {
    companion object
}