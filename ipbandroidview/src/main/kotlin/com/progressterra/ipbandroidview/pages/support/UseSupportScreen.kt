package com.progressterra.ipbandroidview.pages.support

import com.progressterra.ipbandroidview.features.chatinput.UseChatInput
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

interface UseSupportScreen : UseTopBar, UseStateBox, UseChatInput {

    class Empty : UseSupportScreen {

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: StateBoxEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit
    }
}