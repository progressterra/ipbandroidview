package com.progressterra.ipbandroidview.ui.city

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CitySettings(
    val passable: Boolean
) : Parcelable