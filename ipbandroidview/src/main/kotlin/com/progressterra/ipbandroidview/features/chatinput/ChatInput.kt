package com.progressterra.ipbandroidview.features.chatinput

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

@Immutable
data class ChatInputState(
    val input: TextFieldState = TextFieldState(),
    val enabled: Boolean = false
) {

    fun updateMessage(newMessage: String) = copy(input = input.updateText(newMessage))

    fun updateEnabled(enabled: Boolean) =
        copy(enabled = enabled, input = input.updateEnabled(enabled))
}

sealed class ChatInputEvent {
    object Send : ChatInputEvent()
}

interface UseChatInput : UseTextField {

    fun handle(event: ChatInputEvent)
}

@Composable
fun ChatInput(
    modifier: Modifier = Modifier,
    id: String,
    state: ChatInputState,
    useComponent: UseChatInput
) {

}