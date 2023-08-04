package com.progressterra.ipbandroidview.features.orderchat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField
import com.progressterra.ipbandroidview.widgets.messages.Messages

@Composable
fun OrderChat(
    modifier: Modifier = Modifier,
    state: OrderChatState,
    useComponent: UseOrderChat
) {
    if (state.isVisible) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(IpbTheme.colors.surface.asBrush())
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.End),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BrushedText(
                        text = stringResource(id = R.string.close_chat),
                        style = IpbTheme.typography.subHeadlineRegular,
                        tint = IpbTheme.colors.textPrimary2.asBrush()
                    )
                    IconButton(modifier = Modifier.size(24.dp),
                        onClick = { useComponent.handle(OrderChatEvent) }) {
                        BrushedIcon(
                            resId = R.drawable.ic_close,
                            tint = IpbTheme.colors.iconPrimary2.asBrush()
                        )
                    }
                }
                Messages(modifier = Modifier.height(200.dp), state = state.messagesState)
            }
            TextField(
                modifier = modifier.fillMaxWidth(),
                state = state.input,
                useComponent = useComponent,
                hint = stringResource(R.string.message),
                actionIcon = R.drawable.ic_send
            )
        }
    }
}
