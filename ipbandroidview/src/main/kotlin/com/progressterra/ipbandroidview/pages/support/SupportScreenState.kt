package com.progressterra.ipbandroidview.pages.support

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.chatinput.ChatInputState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.widgets.messages.MessagesState

@Immutable
data class SupportScreenState(
    val input: ChatInputState = ChatInputState(),
    val messages: MessagesState = MessagesState(),
    val screenState: ScreenState = ScreenState.LOADING
)