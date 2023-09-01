package com.progressterra.ipbandroidview.pages.interests

import com.progressterra.ipbandroidview.features.interestspicker.InterestsPickerState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState


data class InterestsScreenState(
    val screen: StateColumnState = StateColumnState(),
    val interests: InterestsPickerState,
    val skip: ButtonState = ButtonState(id = "skip"),
    val save: ButtonState = ButtonState(id = "save")
)