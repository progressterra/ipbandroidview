package com.progressterra.ipbandroidview.features.chats

sealed class ChatsEvent(val id: String) {

    class Click(id: String) : ChatsEvent(id)
}