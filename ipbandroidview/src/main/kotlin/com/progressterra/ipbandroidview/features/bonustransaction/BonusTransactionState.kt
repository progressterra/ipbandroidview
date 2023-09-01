package com.progressterra.ipbandroidview.features.bonustransaction


data class BonusTransactionState(
    val amount: String,
    val type: BonusTransactionType,
    val date: String
)