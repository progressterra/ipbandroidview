package com.progressterra.ipbandroidview.pages.datingmain

import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.entities.DatingTarget
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchState

data class DatingMainScreenState(
    val readyToMeet: BrushedSwitchState = BrushedSwitchState(id = "readyToMeet", enabled = false),
    val users: List<DatingUser> = emptyList(),
    val currentUser: DatingUser = DatingUser(),
    val datingTargets: List<DatingTarget> = emptyList(),
    val chosenTarget: DatingTarget? = null,
    val chosenTier: Int? = null,
    val preChosenTier: Int? = null
)