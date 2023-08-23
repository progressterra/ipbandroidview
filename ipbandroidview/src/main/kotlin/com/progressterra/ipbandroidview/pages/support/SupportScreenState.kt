package com.progressterra.ipbandroidview.pages.support

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.supportchat.SupportChatState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumnState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextInputType
import com.progressterra.ipbandroidview.widgets.messages.MessagesState

@Immutable
data class SupportScreenState(
    val input: TextFieldState = TextFieldState(id = "input", type = TextInputType.CHAT),
    val messages: MessagesState = MessagesState(),
    val screen: StateColumnState = StateColumnState(),
    val current: SupportChatState = SupportChatState(),
    val trace: List<SupportChatState> = emptyList()
)