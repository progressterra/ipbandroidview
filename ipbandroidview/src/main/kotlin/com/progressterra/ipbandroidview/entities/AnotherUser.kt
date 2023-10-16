package com.progressterra.ipbandroidview.entities

data class AnotherUser(
    override val id: String = "",
    val image: String = "",
    val hideAvatar: Boolean = false,
    val locationPoint: LocationPoint = LocationPoint(),
    val interests: List<Interest> = emptyList(),
    val distance: String = "",
    val target: String = "",
    val age: String = "",
    val occupation: String = "",
    val connection: DatingConnection = DatingConnection.CAN_CONNECT
) : Id