package com.progressterra.ipbandroidview.pages.signin

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.authorskip.AuthOrSkipState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

@Immutable
data class SignInState(
    val phone: TextFieldState = TextFieldState(),
    val authOrSkipState: AuthOrSkipState = AuthOrSkipState()
) {

    fun updatePhoneValid(valid: Boolean): SignInState = copy(phone = phone.updateValid(valid))

    fun updatePhoneText(text: String): SignInState = copy(phone = phone.updateText(text))

    fun updateAuthButton(enabled: Boolean): SignInState =
        copy(authOrSkipState = authOrSkipState.updateAuthButton(enabled))

    fun updateSkipButton(enabled: Boolean): SignInState =
        copy(authOrSkipState = authOrSkipState.updateSkipButton(enabled))
}