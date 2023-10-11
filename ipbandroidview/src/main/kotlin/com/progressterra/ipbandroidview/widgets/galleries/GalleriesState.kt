package com.progressterra.ipbandroidview.widgets.galleries

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class GalleriesState(
    override val id: String,
    val items: Flow<PagingData<StoreCardState>> = emptyFlow(),
    val title: String = "",
    val state: StateColumnState = StateColumnState()
) : Id