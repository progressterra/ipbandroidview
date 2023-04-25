package com.progressterra.ipbandroidview.features.authorskip

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.button.uEnabled
import com.progressterra.processors.IpbSubState

@Immutable
data class AuthOrSkipState(
    @IpbSubState val auth: ButtonState = ButtonState(
        id = "auth"
    ),
    @IpbSubState val skip: ButtonState = ButtonState(
        id = "skip"
    )
) {

    fun uAuthEnabled(enabled: Boolean): AuthOrSkipState =
        copy(auth = auth.uEnabled(enabled))

    fun uSkipEnabled(enabled: Boolean): AuthOrSkipState =
        copy(skip = skip.uEnabled(enabled))

}