package com.progressterra.android.ipbandroidview.bonuses_banner

data class BonusesInfo(
    val currentQuantity: String,
    val dateBurning: String,
    val forBurningQuantity: String,
    val typeBonusName: String,
    var showAllBonusesInfo: Boolean = currentQuantity != "0" || currentQuantity.isNullOrEmpty()
)