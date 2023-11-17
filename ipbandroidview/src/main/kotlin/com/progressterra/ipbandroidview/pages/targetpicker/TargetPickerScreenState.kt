package com.progressterra.ipbandroidview.pages.targetpicker

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.DatingTarget
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState

@Immutable
data class TargetPickerScreenState(
    val screen: StateColumnState = StateColumnState(),
    val targets: List<DatingTarget> = emptyList(),
    val save: ButtonState = ButtonState(),
    val selectedTarget: DatingTarget = DatingTarget(),
    val skip: ButtonState = ButtonState()
)