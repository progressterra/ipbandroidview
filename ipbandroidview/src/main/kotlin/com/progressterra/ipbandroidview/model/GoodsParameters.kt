package com.progressterra.ipbandroidview.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GoodsParameters(
    val title: String, val description: String
) : Parcelable