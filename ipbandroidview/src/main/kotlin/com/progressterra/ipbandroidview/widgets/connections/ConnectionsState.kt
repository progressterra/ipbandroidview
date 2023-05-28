package com.progressterra.ipbandroidview.widgets.connections

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.avatar.AvatarState

@Immutable
data class ConnectionsState(
    val incoming: List<Item> = emptyList(),
    val accepted: List<Item> = emptyList(),
    val pending: List<Item> = emptyList()
) {

    @Immutable
    data class Item(
        val avatar: AvatarState = AvatarState(),
        val name: String = ""
    )
}