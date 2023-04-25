package com.progressterra.ipbandroidview.pages.signin

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.authorskip.AuthOrSkipState
import com.progressterra.ipbandroidview.features.authorskip.uAuthEnabled
import com.progressterra.ipbandroidview.features.authorskip.uSkipEnabled
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.processors.IpbSubState

@Immutable
data class SignInState(
    @IpbSubState val phone: TextFieldState = TextFieldState(),
    val authOrSkipState: AuthOrSkipState = AuthOrSkipState()
) {

    fun uAuthButton(enabled: Boolean): SignInState =
        copy(authOrSkipState = authOrSkipState.uAuthEnabled(enabled))

    fun uSkipButton(enabled: Boolean): SignInState =
        copy(authOrSkipState = authOrSkipState.uSkipEnabled(enabled))
}