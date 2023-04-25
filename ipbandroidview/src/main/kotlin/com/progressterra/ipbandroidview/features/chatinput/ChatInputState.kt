package com.progressterra.ipbandroidview.features.chatinput

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

@Immutable
data class ChatInputState(
    val input: TextFieldState = TextFieldState(),
    val enabled: Boolean = false
) {

    fun uMessage(newMessage: String) = copy(input = input.uText(newMessage))

    fun uEnabled(enabled: Boolean) =
        copy(enabled = enabled, input = input.uEnabled(enabled))
}