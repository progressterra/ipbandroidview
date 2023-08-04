package com.progressterra.ipbandroidview.features.supportchat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun SupportChat(
    modifier: Modifier = Modifier,
    state: SupportChatState,
    useComponent: UseSupportChat
) {
    if (state.iconRes != 0) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(IpbTheme.colors.surface.asBrush())
                .niceClickable { useComponent.handle(SupportChatEvent(state)) }
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BrushedIcon(
                resId = state.iconRes,
                tint = IpbTheme.colors.primary.asBrush()
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                BrushedText(
                    text = state.title,
                    style = IpbTheme.typography.headline,
                    tint = IpbTheme.colors.textPrimary.asBrush()
                )
                if (state.lastMessage.isNotEmpty()) {
                    BrushedText(
                        text = state.lastMessage,
                        style = IpbTheme.typography.body,
                        tint = IpbTheme.colors.textPrimary.asBrush()
                    )
                }
            }
        }
    } else {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(IpbTheme.colors.surface.asBrush())
                .niceClickable { useComponent.handle(SupportChatEvent(state)) }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BrushedText(
                text = state.title,
                style = IpbTheme.typography.body,
                tint = IpbTheme.colors.textPrimary.asBrush()
            )
            BrushedIcon(
                resId = R.drawable.ic_forw,
                tint = IpbTheme.colors.primary.asBrush()
            )
        }
    }
}
