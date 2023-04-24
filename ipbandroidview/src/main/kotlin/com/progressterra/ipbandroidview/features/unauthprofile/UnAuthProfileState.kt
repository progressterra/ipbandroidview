package com.progressterra.ipbandroidview.features.unauthprofile

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@Immutable
data class UnAuthProfileState(
    val auth: ButtonState = ButtonState(
        id = "auth"
    )
)