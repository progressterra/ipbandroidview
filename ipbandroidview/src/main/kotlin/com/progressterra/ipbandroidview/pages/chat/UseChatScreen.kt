package com.progressterra.ipbandroidview.pages.chat

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseChatScreen : UseTextField, UseTopBar, UseStateColumn {

    class Empty : UseChatScreen {

        override fun handle(event: StateColumnEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit
    }
}