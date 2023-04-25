package com.progressterra.ipbandroidview.features.authorskip

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@Immutable
data class AuthOrSkipState(
    val auth: ButtonState = ButtonState(
        id = "auth"
    ),
    val skip: ButtonState = ButtonState(
        id = "skip"
    )
) {

    fun uAuthEnabled(enabled: Boolean): AuthOrSkipState =
        copy(auth = auth.uEnabled(enabled))

    fun uSkipEnabled(enabled: Boolean): AuthOrSkipState =
        copy(skip = skip.uEnabled(enabled))
}