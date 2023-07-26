package com.progressterra.ipbandroidview.widgets.galleries

import androidx.paging.PagingData
import arrow.optics.optics
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@optics
data class GalleriesState(
    val items: Flow<PagingData<StoreCardState>> = emptyFlow(),
    val title: String = ""
) {

    companion object
}