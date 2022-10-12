package com.progressterra.ipbandroidview.ui.organizations

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Organization(
    val address: String,
    val id: String,
    val warnings: Int,
    val name: String,
    val imageUrl: String,
    val latitude: Double,
    val longitude: Double
) : Parcelable
