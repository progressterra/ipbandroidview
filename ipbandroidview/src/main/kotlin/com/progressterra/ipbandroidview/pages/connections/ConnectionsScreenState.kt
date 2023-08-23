package com.progressterra.ipbandroidview.pages.connections

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumnState
import com.progressterra.ipbandroidview.widgets.connections.ConnectionsState

@Immutable
data class ConnectionsScreenState(
    val connections: ConnectionsState = ConnectionsState(),
    val screen: StateColumnState = StateColumnState(),
)