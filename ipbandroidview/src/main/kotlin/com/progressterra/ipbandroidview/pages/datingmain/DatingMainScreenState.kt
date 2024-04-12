package com.progressterra.ipbandroidview.pages.datingmain

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.entities.DatingTarget
import com.progressterra.ipbandroidview.shared.ui.switch.SwitchState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState

@Immutable
data class DatingMainScreenState(
    val readyToMeet: SwitchState = SwitchState(id = "readyToMeet"),
    val users: List<DatingUser> = emptyList(),
    val currentUser: DatingUser = DatingUser(),
    val datingTargets: List<DatingTarget> = emptyList(),
    val screen: StateColumnState = StateColumnState()
)