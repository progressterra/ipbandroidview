package com.progressterra.ipbandroidview.pages.pfppicker

import com.progressterra.ipbandroidview.features.pfppicker.PfpPickerState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState


data class PfpPickerScreenState(
    val pfpPicker: PfpPickerState = PfpPickerState(),
    val choose: ButtonState = ButtonState(id = "choose"),
    val skip: ButtonState = ButtonState(id = "skip"),
    val screen: StateColumnState = StateColumnState()
)
