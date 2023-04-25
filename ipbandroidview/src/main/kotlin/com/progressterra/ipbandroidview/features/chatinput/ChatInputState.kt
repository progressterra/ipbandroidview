package com.progressterra.ipbandroidview.features.chatinput

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.processors.IpbSubState

@Immutable
data class ChatInputState(
    @IpbSubState val input: TextFieldState = TextFieldState(),
    val enabled: Boolean = false
)