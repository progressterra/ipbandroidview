package com.progressterra.ipbandroidview.widgets.connections

import com.progressterra.ipbandroidview.features.avatar.AvatarState


data class ConnectionsState(
    val incoming: List<Item> = emptyList(),
    val accepted: List<Item> = emptyList(),
    val pending: List<Item> = emptyList()
) {


    data class Item(
        val avatar: AvatarState = AvatarState(),
        val name: String = ""
    )
}