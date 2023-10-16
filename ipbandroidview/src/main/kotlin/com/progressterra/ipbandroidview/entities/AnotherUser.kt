package com.progressterra.ipbandroidview.entities

data class AnotherUser(
    override val id: String = "",
    val image: String = "",
    val hideAvatar: Boolean = false,
    val locationPoint: LocationPoint = LocationPoint()
) : Id