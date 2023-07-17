package com.progressterra.ipbandroidview.pages.main

import com.progressterra.ipbandroidview.features.bonuses.BonusesState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.widgets.galleries.GalleriesState

data class MainState(
    val bonuses: BonusesState = BonusesState(),
    val recommended: List<GalleriesState> = emptyList(),
    val stateBox: ScreenState = ScreenState.LOADING
) {

    fun uStateBox(screenState: ScreenState) = copy(stateBox = screenState)

    fun uRecommended(newRecommended: List<GalleriesState>) = copy(recommended = newRecommended)

    fun uBonuses(bonuses: BonusesState) = copy(bonuses = bonuses)
}
