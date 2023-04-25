package com.progressterra.ipbandroidview.pages.signin

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.authorskip.AuthOrSkipState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

@Immutable
data class SignInState(
    val phone: TextFieldState = TextFieldState(),
    val authOrSkipState: AuthOrSkipState = AuthOrSkipState()
) {

    fun uPhoneValid(valid: Boolean): SignInState = copy(phone = phone.uValid(valid))

    fun uPhoneText(text: String): SignInState = copy(phone = phone.uText(text))

    fun uAuthButton(enabled: Boolean): SignInState =
        copy(authOrSkipState = authOrSkipState.uAuthEnabled(enabled))

    fun uSkipButton(enabled: Boolean): SignInState =
        copy(authOrSkipState = authOrSkipState.uSkipEnabled(enabled))
}