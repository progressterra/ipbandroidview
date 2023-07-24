package com.progressterra.ipbandroidview.features.profilebutton

import androidx.compose.runtime.Immutable
import arrow.optics.optics
import com.progressterra.processors.IpbState

@Immutable
@IpbState
@optics
data class ProfileButtonState(
    val id: String = "",
    val enabled: Boolean = true
) { companion object }