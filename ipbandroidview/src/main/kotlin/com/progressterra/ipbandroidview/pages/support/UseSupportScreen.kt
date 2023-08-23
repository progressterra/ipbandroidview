package com.progressterra.ipbandroidview.pages.support

import com.progressterra.ipbandroidview.features.supportchat.SupportChatEvent
import com.progressterra.ipbandroidview.features.supportchat.UseSupportChat
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseSupportScreen : UseTopBar, UseStateColumn, UseTextField, UseSupportChat {

    class Empty : UseSupportScreen {

        override fun handle(event: SupportChatEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: StateColumnEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit
    }
}