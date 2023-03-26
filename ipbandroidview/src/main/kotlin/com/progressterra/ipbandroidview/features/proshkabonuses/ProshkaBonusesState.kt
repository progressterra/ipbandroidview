package com.progressterra.ipbandroidview.features.proshkabonuses

import androidx.compose.runtime.Immutable

@Immutable
data class ProshkaBonusesState(
    val id: String = "default",
    val bonuses: String = "",
    val canWithdraw: String = "",
    val rate: String = "",
    val loan: String = "",
    val burningDate: String = "",
    val burningQuantity: String = "",
    val isReversed: Boolean = false
)