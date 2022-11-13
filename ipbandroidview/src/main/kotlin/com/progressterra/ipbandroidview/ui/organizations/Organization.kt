package com.progressterra.ipbandroidview.ui.organizations

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class Organization(
    val address: String,
    val id: String,
    val warnings: Int,
    val name: String,
    val imageUrl: String,
    val latitude: Double,
    val longitude: Double
) : Parcelable
