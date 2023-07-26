package com.progressterra.ipbandroidview.features.bonuses

import androidx.compose.runtime.Immutable
import arrow.optics.optics

@Immutable
@optics data class BonusesState(
    val roubles: String = "",
    val bonuses: String = "",
    val burningDate: String = "",
    val burningQuantity: String = ""
) { companion object }