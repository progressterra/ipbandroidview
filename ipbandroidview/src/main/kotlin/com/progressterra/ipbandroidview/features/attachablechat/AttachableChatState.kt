package com.progressterra.ipbandroidview.features.attachablechat

import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextInputType
import com.progressterra.ipbandroidview.widgets.messages.MessagesState

data class AttachableChatState(
    override val id: String = "",
    val messagesState: MessagesState = MessagesState(),
    val input: TextFieldState = TextFieldState(id = "input", type = TextInputType.CHAT),
    val isVisible: Boolean = false,
    val screen: StateColumnState = StateColumnState(id = "chat")
) : Id