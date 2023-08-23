package com.progressterra.ipbandroidview.pages.interests

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.interestspicker.InterestsPickerState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@Immutable
data class InterestsScreenState(
    val screen: StateBoxState = StateBoxState(),
    val interests: InterestsPickerState,
    val skip: ButtonState = ButtonState(id = "skip"),
    val save: ButtonState = ButtonState(id = "save")
)