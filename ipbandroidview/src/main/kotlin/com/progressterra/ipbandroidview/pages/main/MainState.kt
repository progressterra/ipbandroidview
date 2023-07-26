package com.progressterra.ipbandroidview.pages.main

import arrow.optics.optics
import com.progressterra.ipbandroidview.features.bonuses.BonusesState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.widgets.galleries.GalleriesState

@optics
data class MainState(
    val bonuses: BonusesState = BonusesState(),
    val recommended: List<GalleriesState> = emptyList(),
    val stateBox: ScreenState = ScreenState.LOADING
) {
    companion object
}
