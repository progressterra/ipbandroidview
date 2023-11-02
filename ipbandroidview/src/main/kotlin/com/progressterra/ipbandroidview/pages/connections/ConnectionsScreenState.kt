package com.progressterra.ipbandroidview.pages.connections

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.entities.Connection
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


data class ConnectionsScreenState(
    val incoming: Flow<PagingData<Connection>> = emptyFlow(),
    val successIn: Flow<PagingData<Connection>> = emptyFlow(),
    val successOut: Flow<PagingData<Connection>> = emptyFlow(),
    val pending: Flow<PagingData<Connection>> = emptyFlow(),
    val screen: StateColumnState = StateColumnState(),
)