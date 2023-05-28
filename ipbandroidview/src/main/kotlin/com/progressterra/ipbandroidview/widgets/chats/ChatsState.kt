package com.progressterra.ipbandroidview.widgets.chats

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.avatar.AvatarState

@Immutable
data class ChatsState(
    val items: List<Item> = emptyList()
) {

    @Immutable
    data class Item(
        val id: String = "",
        val avatar: AvatarState = AvatarState(),
        val name: String = "",
        val previewMessage: String = "",
        val lastTime: String = ""
    )
}