package com.progressterra.ipbandroidview.pages.welcome

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@Immutable
data class WelcomeState(
    val auth: ButtonState = ButtonState(id = "auth")
)