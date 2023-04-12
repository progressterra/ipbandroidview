package com.progressterra.ipbandroidview.shared.ui.button

import androidx.compose.runtime.Immutable

@Immutable
data class ButtonState(
    val id: String = "",
    val enabled: Boolean = true
) {

    fun updateEnabled(enabled: Boolean): ButtonState = copy(enabled = enabled)
}