package com.progressterra.ipbandroidview.features.chatinput

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextInputType

@Immutable
data class ChatInputState(
    val input: TextFieldState = TextFieldState(
        id = "chat",
        type = TextInputType.CHAT
    )
)