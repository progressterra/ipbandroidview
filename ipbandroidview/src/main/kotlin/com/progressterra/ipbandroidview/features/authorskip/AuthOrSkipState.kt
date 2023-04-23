package com.progressterra.ipbandroidview.features.authorskip

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@Immutable
data class AuthOrSkipState(
    val auth: ButtonState = ButtonState(
        id = "auth", enabled = false
    ),
    val skip: ButtonState = ButtonState(
        id = "skip"
    )
) {

    fun updateAuthButton(enabled: Boolean): AuthOrSkipState =
        copy(auth = auth.updateEnabled(enabled))

    fun updateSkipButton(enabled: Boolean): AuthOrSkipState =
        copy(skip = skip.updateEnabled(enabled))
}