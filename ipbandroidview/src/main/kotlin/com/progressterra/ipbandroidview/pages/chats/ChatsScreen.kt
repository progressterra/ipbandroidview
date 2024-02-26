package com.progressterra.ipbandroidview.pages.chats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.DatingChat
import com.progressterra.ipbandroidview.entities.Sex
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ui.utils.rememberResourceUri
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable

@Composable
fun ChatsScreen(
    modifier: Modifier = Modifier,
    state: ChatsScreenState,
    useComponent: UseChatsScreen
) {

    @Composable
    fun Item(
        itemState: DatingChat
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .niceClickable { useComponent.handle(ChatsScreenEvent.Click(itemState.id)) },
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(79.dp),
                    painter = painterResource(id = R.drawable.avatar_background),
                    contentDescription = null
                )
                SimpleImage(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    image = itemState.avatar.ifEmpty {
                        rememberResourceUri(
                            resourceId = when (itemState.sex) {
                                Sex.MALE -> R.drawable.avatar_male
                                Sex.FEMALE -> R.drawable.avatar_female
                            }
                        ).toString()
                    }
                )
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

    val items = state.items.collectAsLazyPagingItems()
    ThemedLayout(
        modifier = modifier,
        topBar = {
            TopBar(title = stringResource(id = R.string.chats), useComponent = useComponent)
        }
    ) { _, _ ->
        LazyColumn(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
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
}
