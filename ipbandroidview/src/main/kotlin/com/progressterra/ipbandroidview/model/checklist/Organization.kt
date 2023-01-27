package com.progressterra.ipbandroidview.model.checklist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Organization(
    val address: String = "",
    val id: String = "",
    val audits: String = "",
    val documents: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
) : Parcelable
