package com.progressterra.ipbandroidview.pages.signup

import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState


data class SignUpScreenState(
    val editUser: EditUserState = EditUserState(),
    val next: ButtonState = ButtonState(id = "next"),
    val skip: ButtonState = ButtonState(id = "skip"),
    val screen: StateColumnState = StateColumnState()
)
