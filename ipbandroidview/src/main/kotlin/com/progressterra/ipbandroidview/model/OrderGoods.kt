package com.progressterra.ipbandroidview.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderGoods(
    val id: String,
    val inCartCounter: Int,
    val image: String
) : Parcelable
