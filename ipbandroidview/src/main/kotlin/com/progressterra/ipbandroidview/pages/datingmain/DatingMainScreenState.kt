package com.progressterra.ipbandroidview.pages.datingmain

import com.progressterra.ipbandroidview.entities.AnotherUser
import com.progressterra.ipbandroidview.entities.DatingTarget
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchState

data class DatingMainScreenState(
    val readyToMeet: BrushedSwitchState = BrushedSwitchState(id = "readyToMeet", enabled = false),
    val users: List<AnotherUser>,
    val avatar: String,
    val interestsTargets: List<DatingTarget>,
    val chosenInterest: DatingTarget,
    val chosenTier: Int
)