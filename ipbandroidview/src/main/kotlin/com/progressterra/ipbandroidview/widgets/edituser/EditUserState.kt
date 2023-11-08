package com.progressterra.ipbandroidview.widgets.edituser

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Sex
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextInputType

@Immutable
data class EditUserState(
    val name: TextFieldState = TextFieldState(id = "name"),
    val soname: TextFieldState = TextFieldState(id = "soname"),
    val patronymic: TextFieldState = TextFieldState(id = "patronymic"),
    val email: TextFieldState = TextFieldState(id = "email", type = TextInputType.EMAIL),
    val birthday: TextFieldState = TextFieldState(id = "birthday", type = TextInputType.DATE),
    val sex: Sex? = null,
    val sexEnabled: Boolean = true,
)