package com.progressterra.ipbandroidview.entities

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DatingUser(
    override val id: String = "",
    val avatar: String = "",
    val avatarBitmap: Bitmap? = null,
    val name: String = "",
    val description: String = "",
    val hideAvatar: Boolean = false,
    val readyToMeet: Boolean = false,
    val locationPoint: LocationPoint = LocationPoint(),
    val interests: List<Interest> = emptyList(),
    val distance: Int = 0,
    val target: DatingTarget = DatingTarget(),
    val age: String = "",
    val occupation: Interest = Interest(),
    val connection: Connection = Connection(),
    val sex: Sex = Sex.MALE,
    val own: Boolean = false
) : Id, Parcelable, IsEmpty {

    override fun isEmpty() = this == DatingUser()
}