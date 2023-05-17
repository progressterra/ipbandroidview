package com.progressterra.ipbandroidview.features.chatinput

import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseChatInput : UseTextField {

    class Empty : UseChatInput {

        override fun handle(event: TextFieldEvent) = Unit
    }
}
