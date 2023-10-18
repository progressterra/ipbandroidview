package com.progressterra.ipbandroidview.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Interest(
    override val id: String = "",
    val name: String = "",
    val picked: Boolean = false
) : Id, Parcelable
