package com.progressterra.ipbandroidview.features.authorskip

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@Immutable
data class AuthOrSkipState(
    val auth: ButtonState = ButtonState(),
    val skip: ButtonState = ButtonState()
)