package com.progressterra.ipbandroidview.model.store

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GoodsColor(
    val image: String,
    val name: String
) : Parcelable
