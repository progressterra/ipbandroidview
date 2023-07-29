package com.progressterra.ipbandroidview.features.bonuses

import androidx.compose.runtime.Immutable

@Immutable
data class BonusesState(
    val roubles: String = "",
    val bonuses: String = "",
    val burningDate: String = "",
    val burningQuantity: String = ""
)