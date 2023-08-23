package com.progressterra.ipbandroidview.pages.pfppicker

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.pfppicker.PfpPickerState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@Immutable
data class PfpPickerScreenState(
    val pfpPicker: PfpPickerState = PfpPickerState(),
    val choose: ButtonState = ButtonState(id = "choose"),
    val skip: ButtonState = ButtonState(id = "skip"),
    val screen: StateBoxState = StateBoxState()
)
