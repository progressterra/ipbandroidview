package com.progressterra.ipbandroidview.pages.interests

import com.progressterra.ipbandroidview.entities.Interest
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState


data class InterestsScreenState(
    val screen: StateColumnState = StateColumnState(),
    val userInterests: List<Interest> = emptyList(),
    val allInterests: List<Interest> = emptyList(),
    val changedInterests: List<Interest> = emptyList(),
    val skip: ButtonState = ButtonState(id = "skip"),
    val save: ButtonState = ButtonState(id = "save")
)