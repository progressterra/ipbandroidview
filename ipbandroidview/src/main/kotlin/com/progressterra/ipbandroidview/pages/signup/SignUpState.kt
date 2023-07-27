package com.progressterra.ipbandroidview.pages.signup

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState

@Immutable
data class SignUpState(
    val editUser: EditUserState = EditUserState(),
    val next: ButtonState = ButtonState(id = "next"),
    val skip: ButtonState = ButtonState(id = "skip"),
    val screenState: ScreenState = ScreenState.LOADING
)
