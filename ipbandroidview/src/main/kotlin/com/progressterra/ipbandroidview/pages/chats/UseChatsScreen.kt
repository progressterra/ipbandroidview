package com.progressterra.ipbandroidview.pages.chats

interface UseChatsScreen {

    fun handle(event: ChatsScreenEvent)

    class Empty : UseChatsScreen {

        override fun handle(event: ChatsScreenEvent) = Unit

    }
}