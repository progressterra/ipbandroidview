package com.progressterra.ipbandroidview.pages.chats

import com.progressterra.ipbandroidview.features.search.SearchEvent
import com.progressterra.ipbandroidview.features.search.UseSearch
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn
import com.progressterra.ipbandroidview.widgets.chats.ChatsEvent
import com.progressterra.ipbandroidview.widgets.chats.UseChats

interface UseChatsScreen : UseStateColumn, UseSearch, UseChats, UseTopBar {

    class Empty : UseChatsScreen {

        override fun handle(event: SearchEvent) = Unit

        override fun handle(event: StateColumnEvent) = Unit

        override fun handle(event: ChatsEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit
    }
}