package com.progressterra.ipbandroidview.ui.support

import com.progressterra.ipbandroidview.composable.ChatInputState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.Message

data class SupportState(
    val messages: List<Message> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING,
    val chatInput: ChatInputState = ChatInputState()
)
