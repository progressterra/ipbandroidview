package com.progressterra.ipbandroidview.shared.ui.textfield

import androidx.compose.runtime.Immutable
import com.progressterra.processors.IpbState

@Immutable
@IpbState
data class TextFieldState(
    val id: String = "",
    val text: String = "",
    val enabled: Boolean = true,
    val valid: Boolean = true
)