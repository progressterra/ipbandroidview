package com.progressterra.ipbandroidview.widgets.chats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
                        .clip(CircleShape),
                    image = itemState.user.image,
                    backgroundColor = IpbTheme.colors.background.asColor()
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                BrushedText(
                    text = itemState.user.name,
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
