package com.progressterra.ipbandroidview.core

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    val id: String,
    val editable: Boolean
) : Parcelable
