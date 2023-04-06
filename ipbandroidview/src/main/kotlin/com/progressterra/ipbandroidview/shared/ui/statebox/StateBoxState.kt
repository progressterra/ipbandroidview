package com.progressterra.ipbandroidview.shared.ui.statebox

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ScreenState

@Immutable
data class StateBoxState(
    val state: ScreenState = ScreenState.LOADING
) {

    fun updateState(screenState: ScreenState) = copy(state = screenState)
}