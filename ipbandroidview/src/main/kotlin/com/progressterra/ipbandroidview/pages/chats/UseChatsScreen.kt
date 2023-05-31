package com.progressterra.ipbandroidview.pages.chats

import com.progressterra.ipbandroidview.features.avatar.AvatarEvent
import com.progressterra.ipbandroidview.features.search.SearchEvent
import com.progressterra.ipbandroidview.features.search.UseSearch
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox
import com.progressterra.ipbandroidview.widgets.chats.ChatsEvent
import com.progressterra.ipbandroidview.widgets.chats.UseChats

interface UseChatsScreen : UseStateBox, UseSearch, UseChats, UseTopBar {

    class Empty : UseChatsScreen {

        override fun handle(event: AvatarEvent) = Unit

        override fun handle(event: SearchEvent) = Unit

        override fun handle(event: StateBoxEvent) = Unit

        override fun handle(event: ChatsEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit
    }
}