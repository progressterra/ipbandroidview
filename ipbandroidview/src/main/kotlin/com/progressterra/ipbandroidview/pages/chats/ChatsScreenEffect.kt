package com.progressterra.ipbandroidview.pages.chats

sealed class ChatsScreenEffect {

    data class OnChat(val id: String) : ChatsScreenEffect()
}