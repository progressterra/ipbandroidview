package com.progressterra.ipbandroidview.features.chatinput

sealed class ChatInputEvent {

    object Send : ChatInputEvent()
}