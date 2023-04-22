package com.progressterra.ipbandroidview.shared.ui.brushedswitch

import androidx.compose.runtime.Immutable

@Immutable
data class BrushedSwitchState(
    val id: String = "",
    val enabled: Boolean = false,
    val turned: Boolean = false
) {

    fun reverse() = copy(turned = !turned)
}
