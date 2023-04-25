package com.progressterra.ipbandroidview.shared.ui.button

import androidx.compose.runtime.Immutable
import com.progressterra.processors.IpbState

@Immutable
@IpbState
data class ButtonState(
    val id: String = "",
    val enabled: Boolean = true
)