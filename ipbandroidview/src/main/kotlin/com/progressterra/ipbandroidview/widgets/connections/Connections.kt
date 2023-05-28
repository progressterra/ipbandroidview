package com.progressterra.ipbandroidview.widgets.connections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.avatar.Avatar
import com.progressterra.ipbandroidview.features.avatar.AvatarState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun Connections(
    modifier: Modifier = Modifier,
    state: ConnectionsState,
    useComponent: UseConnections
) {

    @Composable
    fun Item(
        itemState: ConnectionsState.Item
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Avatar(
                state = itemState.avatar,
                useComponent = useComponent
            )
            BrushedText(
                text = itemState.name,
                style = IpbTheme.typography.caption,
                tint = IpbTheme.colors.textSecondary.asBrush()
            )
        }
    }

    @Composable
    fun Category(
        name: String,
        items: List<ConnectionsState.Item>
    ) {
        BrushedText(
            text = name,
            style = IpbTheme.typography.title,
            tint = IpbTheme.colors.textPrimary.asBrush()
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(26.dp)
        ) {
            items(items) {
                Item(it)
            }
        }
    }

    Column(
        modifier = modifier.padding(horizontal = 28.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        if (state.incoming.isNotEmpty()) {
            Category(
                name = "${stringResource(R.string.you_have)} ${state.incoming.size} ${
                    stringResource(
                        R.string.incoming_connections
                    )
                }",
                items = state.incoming
            )
        }
        if (state.accepted.isNotEmpty()) {
            Category(
                name = stringResource(R.string.accepted_connections),
                items = state.accepted
            )
        }
        if (state.pending.isNotEmpty()) {
            Category(
                name = stringResource(R.string.pending_connections),
                items = state.pending
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PreviewConnections() {
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

        Connections(
            state = connectionsState,
            useComponent = UseConnections.Empty()
        )
    }
}
