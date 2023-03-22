package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ui.TextField
import com.progressterra.ipbandroidview.shared.ui.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.UseTextField
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

data class ChatInputState(
    val message: TextFieldState = TextFieldState(), val enabled: Boolean = false
) {

    fun updateMessage(newMessage: String) = copy(message = message.updateText(newMessage))

    fun updateEnabled(enabled: Boolean) =
        copy(enabled = enabled, message = message.updateEnabled(enabled))
}

sealed class ChatInputEvent {
    object Send : ChatInputEvent()
}

interface UseChatInput : UseTextField {

    fun handleEvent(id: String, event: ChatInputEvent)
}

/**
 * message - text field
 */
@Composable
fun ChatInput(
    modifier: Modifier = Modifier,
    id: String,
    state: ChatInputState,
    useComponent: UseChatInput
) {
    BottomHolder(modifier = modifier) {
        Text(
            text = stringResource(R.string.support_info),
            color = IpbTheme.colors.gray2,
            style = IpbTheme.typography.tertiary
        )
        Spacer(modifier = Modifier.size(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier.weight(1f),
                state = state.message,
                useComponent = useComponent,
                id = "message"
            )
            IconButton(enabled = state.enabled,
                onClick = { useComponent.handleEvent(id, ChatInputEvent.Send) }) {
                SendIcon()
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
    }
}