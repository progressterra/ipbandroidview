package com.progressterra.ipbandroidview.pages.chat

import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextInputType
import com.progressterra.ipbandroidview.widgets.messages.MessagesState

data class ChatScreenState(
    override val id: String = "",
    val name: String = "",
    val input: TextFieldState = TextFieldState(id = "input", type = TextInputType.CHAT),
    val messages: MessagesState = MessagesState(),
    val screen: StateColumnState = StateColumnState()
) : Id