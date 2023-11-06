package com.progressterra.ipbandroidview.pages.connections

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


data class ConnectionsScreenState(
    val incoming: Flow<PagingData<DatingUser>> = emptyFlow(),
    val successIn: Flow<PagingData<DatingUser>> = emptyFlow(),
    val successOut: Flow<PagingData<DatingUser>> = emptyFlow(),
    val pending: Flow<PagingData<DatingUser>> = emptyFlow(),
    val screen: StateColumnState = StateColumnState(),
)