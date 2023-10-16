package com.progressterra.ipbandroidview.pages.datingmain

import com.progressterra.ipbandroidview.entities.AnotherUser
import com.progressterra.ipbandroidview.entities.DatingTarget
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchState

data class DatingMainScreenState(
    val readyToMeet: BrushedSwitchState = BrushedSwitchState(id = "readyToMeet"),
    val users: List<AnotherUser> = emptyList(),
    val avatar: String = "",
    val interestsTargets: List<DatingTarget> = emptyList(),
    val chosenTarget: DatingTarget? = null,
    val chosenTier: Int? = null,
    val preChosenTier: Int? = null
)