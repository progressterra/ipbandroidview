package com.progressterra.ipbandroidview.pages.chats

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar

interface UseChatsScreen : UseTopBar {

    fun handle(event: ChatsScreenEvent)

    class Empty : UseChatsScreen {

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: ChatsScreenEvent) = Unit

    }
}