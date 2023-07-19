package com.progressterra.ipbandroidview.features.profilebutton

import androidx.compose.runtime.Immutable
import com.progressterra.processors.IpbState

@Immutable
@IpbState
data class ProfileButtonState(
    val id: String = "",
    val enabled: Boolean = true
)