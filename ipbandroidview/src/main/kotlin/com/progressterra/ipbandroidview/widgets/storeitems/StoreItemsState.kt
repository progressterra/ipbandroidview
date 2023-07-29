package com.progressterra.ipbandroidview.widgets.storeitems

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class StoreItemsState(
    val items: Flow<PagingData<StoreCardState>> = emptyFlow()
)