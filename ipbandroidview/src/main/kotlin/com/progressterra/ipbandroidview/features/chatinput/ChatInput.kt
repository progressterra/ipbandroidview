package com.progressterra.ipbandroidview.features.chatinput

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseChatInput : UseTextField {

    fun handle(event: ChatInputEvent)
}

@Composable
fun ChatInput(
    modifier: Modifier = Modifier,
    id: String,
    state: ChatInputState,
    useComponent: UseChatInput
) {

}