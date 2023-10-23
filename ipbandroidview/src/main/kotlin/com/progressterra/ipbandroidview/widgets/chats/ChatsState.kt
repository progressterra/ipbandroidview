package com.progressterra.ipbandroidview.widgets.chats

import com.progressterra.ipbandroidview.entities.DatingUser


data class ChatsState(
    val items: List<Item> = emptyList()
) {


    data class Item(
        val id: String = "",
        val user: DatingUser = DatingUser(),
        val previewMessage: String = "",
        val lastTime: String = ""
    )
}