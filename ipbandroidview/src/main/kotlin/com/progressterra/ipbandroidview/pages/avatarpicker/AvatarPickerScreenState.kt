package com.progressterra.ipbandroidview.pages.avatarpicker

import com.progressterra.ipbandroidview.features.avatarpicker.AvatarPickerState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState


data class AvatarPickerScreenState(
    val avatars: AvatarPickerState = AvatarPickerState(),
    val screen: StateColumnState = StateColumnState(),
    val confirm: ButtonState = ButtonState(id = "confirm"),
    val skip: ButtonState = ButtonState(id = "skip")
)
