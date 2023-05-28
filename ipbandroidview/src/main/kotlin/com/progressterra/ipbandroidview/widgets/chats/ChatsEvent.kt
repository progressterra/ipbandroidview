package com.progressterra.ipbandroidview.widgets.chats

sealed class ChatsEvent(val id: String) {

    class Click(id: String) : ChatsEvent(id)
}