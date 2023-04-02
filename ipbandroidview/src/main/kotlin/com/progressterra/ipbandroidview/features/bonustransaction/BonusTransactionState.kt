package com.progressterra.ipbandroidview.features.bonustransaction

import androidx.compose.runtime.Immutable

@Immutable
data class BonusTransactionState(
    val amount: String,
    val type: BonusTransactionType,
    val date: String
)