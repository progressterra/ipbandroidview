package com.progressterra.ipbandroidview.features.attachablechat

import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.widgets.messages.MessagesState

data class AttachableChatState(
    val messagesState: MessagesState,
    val input: TextFieldState,
    val isVisible: Boolean
)