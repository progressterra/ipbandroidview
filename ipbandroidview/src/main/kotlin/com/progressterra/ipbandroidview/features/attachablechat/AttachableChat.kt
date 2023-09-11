package com.progressterra.ipbandroidview.features.attachablechat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.niceClickable
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField
import com.progressterra.ipbandroidview.widgets.messages.Messages

@Composable
fun AttachableChat(
    modifier: Modifier = Modifier,
    state: AttachableChatState,
    canBeClosed: Boolean,
    title: String = "",
    useComponent: UseAttachableChat
) {
    if (state.isVisible) {
        Column(
            modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(IpbTheme.colors.surface.asBrush())
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BrushedText(
                        text = title,
                        style = IpbTheme.typography.subHeadlineBold,
                        tint = IpbTheme.colors.textPrimary.asBrush()
                    )
                    if (canBeClosed) {
                        Row(
                            modifier = Modifier
                                .clip(CircleShape)
                                .niceClickable { useComponent.handle(AttachableChatEvent) },
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            BrushedText(
                                text = stringResource(id = R.string.close_chat),
                                style = IpbTheme.typography.subHeadlineRegular,
                                tint = IpbTheme.colors.textPrimary2.asBrush()
                            )
                            BrushedIcon(
                                modifier = Modifier.size(24.dp),
                                resId = R.drawable.ic_close,
                                tint = IpbTheme.colors.iconPrimary2.asBrush()
                            )
                        }
                    }
                }
                Messages(
                    state = state.messagesState,
                    messagesBackground = IpbTheme.colors.background.asBrush()
                )
            }
            TextField(
                modifier = Modifier.fillMaxWidth(),
                state = state.input,
                useComponent = useComponent,
                hint = stringResource(R.string.message)
            )
        }
    }
}

@Preview
@Composable
private fun AttachableChatPreview() {
    IpbTheme {
        AttachableChat(
            state = AttachableChatState(isVisible = true),
            title = "Hello",
            canBeClosed = true,
            useComponent = UseAttachableChat.Empty()
        )
    }
}
