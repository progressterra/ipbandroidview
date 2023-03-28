package com.progressterra.ipbandroidview.ui.support

import com.progressterra.ipbandroidview.composable.ChatInputEvent
import com.progressterra.ipbandroidview.composable.UseChatInput
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

interface SupportInteractor : UseChatInput {

    fun onBack()

    fun refresh()

    class Empty : SupportInteractor {

        override fun handle(event: ChatInputEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit

        override fun onBack() = Unit

        override fun refresh() = Unit
    }
}