package com.progressterra.ipbandroidview.features.bonuses

import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState


data class BonusesState(
    val roubles: String = "",
    val hasCards: Boolean = false,
    val state: StateColumnState = StateColumnState(id = "bonuses")
)