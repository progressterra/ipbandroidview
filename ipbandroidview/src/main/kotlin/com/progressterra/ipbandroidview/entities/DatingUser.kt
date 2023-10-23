package com.progressterra.ipbandroidview.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DatingUser(
    override val id: String = "",
    val image: String = "",
    val name: String = "",
    val description: String = "",
    val hideAvatar: Boolean = false,
    val locationPoint: LocationPoint = LocationPoint(),
    val interests: List<Interest> = emptyList(),
    val distance: Int = 0,
    val target: String = "",
    val age: String = "",
    val occupation: String = "",
    val connection: DatingConnection = DatingConnection.CAN_CONNECT,
    val connectionId: String = ""
) : Id, Parcelable