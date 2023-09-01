package com.progressterra.ipbandroidview.pages.connections

import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.widgets.connections.ConnectionsState


data class ConnectionsScreenState(
    val connections: ConnectionsState = ConnectionsState(),
    val screen: StateColumnState = StateColumnState(),
)