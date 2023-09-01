package com.progressterra.ipbandroidview.widgets.chats

import com.progressterra.ipbandroidview.features.avatar.AvatarState


data class ChatsState(
    val items: List<Item> = emptyList()
) {


    data class Item(
        val id: String = "",
        val avatar: AvatarState = AvatarState(),
        val name: String = "",
        val previewMessage: String = "",
        val lastTime: String = ""
    )
}