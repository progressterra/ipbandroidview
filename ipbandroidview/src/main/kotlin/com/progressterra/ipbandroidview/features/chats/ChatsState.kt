package com.progressterra.ipbandroidview.features.chats

import androidx.compose.runtime.Immutable

@Immutable
data class ChatsState(
    val items: List<Item> = emptyList()
) {

    @Immutable
    data class Item(
        val id: String = "",
        val avatarUrl: String = "",
        val online: Boolean = false,
        val name: String = "",
        val previewMessage: String = "",
        val lastTime: String = ""
    )
}