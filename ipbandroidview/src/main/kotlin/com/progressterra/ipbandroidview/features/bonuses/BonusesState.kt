package com.progressterra.ipbandroidview.features.bonuses

import androidx.compose.runtime.Immutable

@Immutable
data class BonusesState(
    val bonuses: String = "",
    val canWithdraw: String = "",
    val rate: String = "",
    val loan: String = "",
    val burningDate: String = "",
    val burningQuantity: String = ""
)