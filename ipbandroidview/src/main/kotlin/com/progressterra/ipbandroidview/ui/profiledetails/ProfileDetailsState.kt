package com.progressterra.ipbandroidview.ui.profiledetails

import com.progressterra.ipbandroidview.composable.component.ButtonState
import com.progressterra.ipbandroidview.composable.component.TextFieldState

data class ProfileDetailsState(
    val phone: TextFieldState = TextFieldState(),
    val name: TextFieldState = TextFieldState(),
    val email: TextFieldState = TextFieldState(),
    val confirm: ButtonState = ButtonState(),
    val logout: ButtonState = ButtonState()
) {

    fun updatePhone(newPhone: String) = copy(phone = phone.updateText(newPhone))

    fun updateTextFieldsEnabled(enabled: Boolean) = copy(
        phone = phone.updateEnabled(enabled),
        name = name.updateEnabled(enabled),
        email = email.updateEnabled(enabled)
    )

    fun updateName(newName: String) = copy(name = name.updateText(newName))

    fun updateEmail(newEmail: String) = copy(email = email.updateText(newEmail))
}
