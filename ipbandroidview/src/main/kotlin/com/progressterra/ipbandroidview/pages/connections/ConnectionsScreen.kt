package com.progressterra.ipbandroidview.pages.connections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.niceClickable
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn

@Composable
fun ConnectionsScreen(
    modifier: Modifier = Modifier,
    state: ConnectionsScreenState,
    useComponent: UseConnectionsScreen
) {

    @Composable
    fun Item(
        itemState: DatingUser
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                BrushedIcon(
                    modifier = Modifier.size(79.dp),
                    resId = R.drawable.avatar_background,
                    tint = IpbTheme.colors.primary.asBrush()
                )
                SimpleImage(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .niceClickable { useComponent.handle(ConnectionsScreenEvent(itemState)) },
                    image = itemState.avatar,
                    backgroundColor = IpbTheme.colors.background.asColor()
                )
            }
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
        items: LazyPagingItems<DatingUser>
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
            items(
                count = items.itemCount,
                key = items.itemKey { it.id }
            ) { index ->
                items[index]?.let {
                    Item(it)
                }
            }
        }
    }

    ThemedLayout(
        modifier = modifier,
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

            Column(
                modifier = Modifier.padding(horizontal = 28.dp),
                verticalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                val incomingItems = state.incoming.collectAsLazyPagingItems()
                Category(
                    name = "${stringResource(R.string.you_have)} ${incomingItems.itemCount} ${
                        stringResource(
                            R.string.incoming_connections
                        )
                    }",
                    items = incomingItems
                )
                val successInItems = state.successIn.collectAsLazyPagingItems()
                val successOutItems = state.successOut.collectAsLazyPagingItems()
                BrushedText(
                    text = stringResource(R.string.accepted_connections),
                    style = IpbTheme.typography.title,
                    tint = IpbTheme.colors.textPrimary.asBrush()
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(26.dp)
                ) {
                    items(
                        count = successInItems.itemCount,
                        key = successInItems.itemKey { it.id }
                    ) { index ->
                        successInItems[index]?.let {
                            Item(it)
                        }
                    }
                    items(
                        count = successOutItems.itemCount,
                        key = successOutItems.itemKey { it.id }
                    ) { index ->
                        successOutItems[index]?.let {
                            Item(it)
                        }
                    }
                }
                val pendingItems = state.pending.collectAsLazyPagingItems()
                Category(
                    name = stringResource(R.string.pending_connections),
                    items = pendingItems
                )

            }
        }
    }
}