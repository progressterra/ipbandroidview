package com.progressterra.ipbandroidview.widgets.chats

import com.progressterra.ipbandroidview.features.avatar.AvatarEvent
import com.progressterra.ipbandroidview.features.avatar.UseAvatar

interface UseChats : UseAvatar {

    fun handle(event: ChatsEvent)

    class Empty : UseChats {

        override fun handle(event: ChatsEvent) = Unit

        override fun handle(event: AvatarEvent) = Unit
    }
}