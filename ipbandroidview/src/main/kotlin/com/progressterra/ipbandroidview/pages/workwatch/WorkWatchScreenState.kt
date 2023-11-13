package com.progressterra.ipbandroidview.pages.workwatch

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@Immutable
data class WorkWatchScreenState(
    val ask: ButtonState = ButtonState(id = "ask"),
    val enable: ButtonState = ButtonState(id = "enable")
)