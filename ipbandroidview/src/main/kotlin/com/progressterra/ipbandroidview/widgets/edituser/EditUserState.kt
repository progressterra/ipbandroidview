package com.progressterra.ipbandroidview.widgets.edituser

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextInputType

@Immutable
data class EditUserState(
    val name: TextFieldState = TextFieldState(id = "name"),
    val email: TextFieldState = TextFieldState(id = "email"),
    val phone: TextFieldState = TextFieldState(
        id = "phone",
        type = TextInputType.PHONE_NUMBER
    ),
    val birthday: TextFieldState = TextFieldState(
        id = "birthday",
        type = TextInputType.DATE
    )
) {
    companion object
}