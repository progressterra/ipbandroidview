package com.progressterra.ipbandroidview.data.model

import com.progressterra.ipbandroidapi.api.ibonus.model.GeneralInfo

data class BonusesInfo(
    val currentQuantity: Int,
    val dateBurning: String,
    val forBurningQuantity: Int,
    val typeBonusName: String,
    var showAllBonusesInfo: Boolean = currentQuantity != 0
) {
    constructor(data: GeneralInfo) : this(data.currentQuantity.toInt(), data.dateBurning, data.forBurningQuantity.toInt(), data.typeBonusName)
}