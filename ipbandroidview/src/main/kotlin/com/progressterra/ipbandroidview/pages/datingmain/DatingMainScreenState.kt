package com.progressterra.ipbandroidview.pages.datingmain

import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchState

data class DatingMainScreenState(
    val readyToMeet: BrushedSwitchState = BrushedSwitchState(id = "readyToMeet", enabled = false)
)