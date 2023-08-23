package com.progressterra.ipbandroidview.pages.connections

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.avatar.AvatarState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import com.progressterra.ipbandroidview.widgets.connections.Connections
import com.progressterra.ipbandroidview.widgets.connections.ConnectionsState

@Composable
fun ConnectionsScreen(
    state: ConnectionsScreenState,
    useComponent: UseConnectionsScreen
) {
    ThemedLayout(
        topBar = {
            TopBar(
                title = stringResource(R.string.connections),
                showBackButton = true,
                useComponent = useComponent
            )
        }
    ) { _, _ ->
        StateColumn(
            state = state.screen,
            useComponent = useComponent
        ) {
            Connections(
                modifier = Modifier.padding(top = 40.dp),
                state = state.connections,
                useComponent = useComponent
            )
        }
    }
}

@Preview
@Composable
private fun ConnectionsScreenPreview() {
    IpbTheme {

        val incoming = listOf(
            ConnectionsState.Item(
                avatar = AvatarState(
                    url = "https://example.com/avatar1.jpg",
                    online = true
                ),
                name = "John Doe"
            ),
            ConnectionsState.Item(
                avatar = AvatarState(
                    url = "https://example.com/avatar2.jpg",
                    online = false
                ),
                name = "Jane Doe"
            )
        )

        val accepted = listOf(
            ConnectionsState.Item(
                avatar = AvatarState(
                    url = "https://example.com/avatar3.jpg",
                    online = true
                ),
                name = "Dave Smith"
            )
        )

        val pending = listOf(
            ConnectionsState.Item(
                avatar = AvatarState(
                    url = "https://example.com/avatar4.jpg",
                    online = false
                ),
                name = "Mary Johnson"
            )
        )

        val connectionsState = ConnectionsState(
            incoming = incoming,
            accepted = accepted,
            pending = pending
        )

        val connectionsScreenState = ConnectionsScreenState(
            connections = connectionsState,
            screen = StateColumnState(state = ScreenState.SUCCESS)
        )

        ConnectionsScreen(
            state = connectionsScreenState,
            useComponent = UseConnectionsScreen.Empty()
        )
    }
}
