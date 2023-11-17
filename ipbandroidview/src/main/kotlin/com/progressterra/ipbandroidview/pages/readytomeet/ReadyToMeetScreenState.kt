package com.progressterra.ipbandroidview.pages.readytomeet

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.DatingTarget
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState

@Immutable
data class ReadyToMeetScreenState(
    val screen: StateColumnState = StateColumnState(),
    val target: DatingTarget = DatingTarget(),
    val user: DatingUser = DatingUser(),
    val readyToMeet: Boolean = false,
    val save: ButtonState = ButtonState()
)