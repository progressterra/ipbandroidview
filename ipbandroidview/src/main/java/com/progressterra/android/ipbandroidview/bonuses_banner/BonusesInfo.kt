package com.progressterra.android.ipbandroidview.bonuses_banner

import com.google.gson.annotations.SerializedName

data class BonusesInfo(
    val currentQuantity: Int,
    val dateBurning: String,
    val forBurningQuantity: Int,
    val typeBonusName: String
)