package com.progressterra.ipbandroidview.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderGoods(
    val id: String,
    val image: String,
    val name: String,
    val inCartCounter: Int,
    val totalPrice: SimplePrice
) : Parcelable
