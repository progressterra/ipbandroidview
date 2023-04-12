package com.progressterra.ipbandroidview.features.proshkabonuses

import androidx.compose.runtime.Immutable

@Immutable
data class ProshkaBonusesState(
    val bonuses: String = "",
    val canWithdraw: String = "",
    val rate: String = "",
    val loan: String = "",
    val burningDate: String = "",
    val burningQuantity: String = ""
)