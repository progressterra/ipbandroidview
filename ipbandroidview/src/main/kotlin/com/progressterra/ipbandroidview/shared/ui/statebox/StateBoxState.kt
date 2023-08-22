package com.progressterra.ipbandroidview.shared.ui.statebox

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Id

@Immutable
data class StateBoxState(
    override val id: String = "",
    val state: ScreenState = ScreenState.LOADING
) : Id
