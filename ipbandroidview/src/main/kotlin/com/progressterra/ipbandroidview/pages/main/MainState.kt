package com.progressterra.ipbandroidview.pages.main

import com.progressterra.ipbandroidview.features.bonuses.BonusesState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import com.progressterra.ipbandroidview.widgets.galleries.GalleriesState

data class MainState(
    val bonuses: BonusesState = BonusesState(),
    val recommended: List<GalleriesState> = emptyList(),
    val screen: StateBoxState = StateBoxState()
)
