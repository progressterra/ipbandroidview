package com.progressterra.ipbandroidview.pages.chats

sealed class ChatsScreenEvent(val id: String) {

    class Click(id: String) : ChatsScreenEvent(id)
}