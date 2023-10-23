package com.progressterra.ipbandroidview.widgets.chats

interface UseChats {

    fun handle(event: ChatsEvent)

    class Empty : UseChats {

        override fun handle(event: ChatsEvent) = Unit

    }
}