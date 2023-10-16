package com.progressterra.ipbandroidview.widgets.edituser

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Sex
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextInputType

@Immutable
data class EditUserState(
    val name: TextFieldState = TextFieldState(id = "name", type = TextInputType.NAME_SURNAME),
    val email: TextFieldState = TextFieldState(id = "email", type = TextInputType.EMAIL),
    val phone: TextFieldState = TextFieldState(
        id = "phone",
        type = TextInputType.PHONE_NUMBER
    ),
    val birthday: TextFieldState = TextFieldState(
        id = "birthday",
        type = TextInputType.DATE
    ),
    val sex: Sex = Sex.NONE
)