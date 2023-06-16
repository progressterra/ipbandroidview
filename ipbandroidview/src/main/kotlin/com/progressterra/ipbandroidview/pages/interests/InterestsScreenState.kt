package com.progressterra.ipbandroidview.pages.interests

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.interestspicker.InterestsPickerState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@Immutable
data class InterestsScreenState(
    val screen: ScreenState = ScreenState.LOADING,
    val interests: InterestsPickerState,
    val skip: ButtonState = ButtonState(id = "skip"),
    val save: ButtonState = ButtonState(id = "save")
)