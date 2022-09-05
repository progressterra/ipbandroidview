package com.progressterra.ipbandroidview.data.model

data class Purchase(
    val addedBonusesSum: Int,
    val purchaseDate: String,
    val purchaseId: String,
    val purchaseSum: Int,
    val shopName: String,
    val spentBonusesSum: Int
)