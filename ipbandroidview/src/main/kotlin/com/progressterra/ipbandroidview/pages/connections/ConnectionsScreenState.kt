package com.progressterra.ipbandroidview.pages.connections

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.widgets.connections.ConnectionsState

@Immutable
data class ConnectionsScreenState(
    val connections: ConnectionsState = ConnectionsState(),
    val screen: ScreenState = ScreenState.LOADING,
)