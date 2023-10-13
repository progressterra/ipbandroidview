package com.progressterra.ipbandroidview.entities

data class AnotherUser(
    override val id: String = "",
    val image: String = "",
    val hideAvatar: Boolean = false
) : Id