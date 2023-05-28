package com.progressterra.ipbandroidview.features.chats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun Chats(
    modifier: Modifier = Modifier,
    state: ChatsState,
    useComponent: UseChats
) {

    @Composable
    fun Item(
        itemState: ChatsState.Item
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .niceClickable { useComponent.handle(ChatsEvent.Click(itemState.id)) },
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                BrushedIcon(
                    modifier = Modifier.size(79.dp),
                    resId = R.drawable.avatar_background,
                    tint = IpbTheme.colors.primary.asBrush()
                )
                SimpleImage(
                    modifier = Modifier.size(64.dp).clip(CircleShape),
                    url = itemState.avatarUrl,
                    backgroundColor = IpbTheme.colors.background.asColor()
                )
                if (itemState.online) {
                    BrushedIcon(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 4.dp, bottom = 9.dp),
                        resId = R.drawable.ic_online,
                        tint = IpbTheme.colors.primary.asBrush()
                    )
                }
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                BrushedText(
                    text = itemState.name,
                    style = IpbTheme.typography.headline,
                    tint = IpbTheme.colors.textPrimary.asBrush()
                )
                BrushedText(
                    text = itemState.previewMessage,
                    style = IpbTheme.typography.body,
                    tint = IpbTheme.colors.textPrimary.asBrush()
                )
            }
            BrushedText(
                text = itemState.lastTime,
                style = IpbTheme.typography.body,
                tint = IpbTheme.colors.textPrimary.asBrush()
            )
        }
    }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(state.items) {
            Item(it)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PreviewChats() {
    IpbTheme {

        val itemsState = listOf(
            ChatsState.Item(
                avatarUrl = "https://example.com/avatar1.jpg",
                online = true,
                name = "John Doe",
                previewMessage = "Hello, how are you?",
                lastTime = "12ч"
            ),
            ChatsState.Item(
                avatarUrl = "https://example.com/avatar2.jpg",
                online = false,
                name = "Jane Doe",
                previewMessage = "Let's catch up later!",
                lastTime = "5м"
            ),
            ChatsState.Item(
                avatarUrl = "https://example.com/avatar3.jpg",
                online = true,
                name = "Dave Smith",
                previewMessage = "Are you available for a meeting?",
                lastTime = "3ч"
            ),
            ChatsState.Item(
                avatarUrl = "https://example.com/avatar4.jpg",
                online = false,
                name = "Mary Johnson",
                previewMessage = "Can't wait to see you at the event!",
                lastTime = "2ч"
            )
        )
        Chats(
            state = ChatsState(itemsState),
            useComponent = UseChats.Empty()
        )
    }
}
