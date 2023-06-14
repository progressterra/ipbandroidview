package com.progressterra.ipbandroidview.pages.info

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.info.InfoState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@Immutable
data class InfoScreenState(
    val info: InfoState = InfoState(),
    val save: ButtonState = ButtonState(id = "save"),
    val skip: ButtonState = ButtonState(id = "skip"),
    val screen: ScreenState = ScreenState.LOADING
)