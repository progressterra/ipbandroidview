package com.progressterra.ipbandroidview.pages.pfpsetter

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.pfpsetter.PfpSetterState

@Immutable
data class PfpSetterScreenState(
    val pfpPicker: PfpSetterState = PfpSetterState()
)
