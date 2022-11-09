package com.progressterra.ipbandroidview.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GoodsParameters(
    val title: String, val description: String
) : Parcelable