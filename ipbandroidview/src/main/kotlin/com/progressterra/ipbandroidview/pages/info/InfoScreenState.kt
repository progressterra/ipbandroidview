package com.progressterra.ipbandroidview.pages.info

import com.progressterra.ipbandroidview.features.info.InfoState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState


data class InfoScreenState(
    val info: InfoState = InfoState(),
    val save: ButtonState = ButtonState(id = "save"),
    val skip: ButtonState = ButtonState(id = "skip"),
    val screen: StateColumnState = StateColumnState()
)