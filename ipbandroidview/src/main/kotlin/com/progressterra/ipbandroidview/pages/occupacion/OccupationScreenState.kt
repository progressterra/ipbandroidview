package com.progressterra.ipbandroidview.pages.occupacion

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Interest
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState

@Immutable
data class OccupationScreenState(
    val screen: StateColumnState = StateColumnState(),
    val allOccupations: List<Interest> = emptyList(),
    val userOccupation: Interest? = null,
    val pickedOccupation: Interest? = null,
    val skip: ButtonState = ButtonState(id = "skip"),
    val save: ButtonState = ButtonState(id = "save", enabled = false)
)