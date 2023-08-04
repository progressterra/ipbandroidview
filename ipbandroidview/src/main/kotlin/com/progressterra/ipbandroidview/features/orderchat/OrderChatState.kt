package com.progressterra.ipbandroidview.features.orderchat

import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.widgets.messages.MessagesState

data class OrderChatState(
    val messagesState: MessagesState = MessagesState(),
    val input: TextFieldState = TextFieldState(id = "input"),
    val isVisible: Boolean = false
)