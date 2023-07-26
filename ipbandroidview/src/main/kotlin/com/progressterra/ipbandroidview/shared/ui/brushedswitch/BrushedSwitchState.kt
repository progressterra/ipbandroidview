package com.progressterra.ipbandroidview.shared.ui.brushedswitch

import androidx.compose.runtime.Immutable
import arrow.optics.optics

@Immutable
@optics
data class BrushedSwitchState(
    val id: String = "",
    val enabled: Boolean = true,
    val turned: Boolean = false
) {

    companion object
}
