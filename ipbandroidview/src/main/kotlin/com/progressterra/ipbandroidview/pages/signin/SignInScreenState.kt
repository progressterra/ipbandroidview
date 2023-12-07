package com.progressterra.ipbandroidview.pages.signin

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextInputType

@Immutable
data class SignInScreenState(
    val phone: TextFieldState = TextFieldState(
        type = TextInputType.PHONE_NUMBER,
        id = "phone",
        text = "7"
    ),
    val auth: ButtonState = ButtonState(
        id = "auth",
        enabled = false
    ),
    val skip : ButtonState = ButtonState(id = "skip")
)