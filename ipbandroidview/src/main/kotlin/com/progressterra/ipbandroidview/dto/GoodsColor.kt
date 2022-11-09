package com.progressterra.ipbandroidview.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GoodsColor(
    val image: String,
    val name: String
) : Parcelable
