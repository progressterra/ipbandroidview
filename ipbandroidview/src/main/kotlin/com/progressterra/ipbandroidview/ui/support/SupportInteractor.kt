package com.progressterra.ipbandroidview.ui.support

import com.progressterra.ipbandroidview.composable.ChatInputEvent
import com.progressterra.ipbandroidview.composable.UseChatInput
import com.progressterra.ipbandroidview.composable.component.TextFieldComponentEvent

interface SupportInteractor : UseChatInput {

    fun onBack()

    fun refresh()

    class Empty : SupportInteractor {

        override fun handleEvent(id: String, event: ChatInputEvent) = Unit

        override fun handleEvent(id: String, event: TextFieldComponentEvent) = Unit

        override fun onBack() = Unit

        override fun refresh() = Unit
    }
}