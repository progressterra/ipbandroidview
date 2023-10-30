package com.progressterra.ipbandroidview.pages.datingprofile

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.entities.Interest
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextInputType

@Immutable
data class DatingProfileScreenState(
    val user: DatingUser = DatingUser(),
    val save: ButtonState = ButtonState(id = "save"),
    val chat: ButtonState = ButtonState(id = "chat"),
    val connect: ButtonState = ButtonState(id = "connect"),
    val choosePhoto: ButtonState = ButtonState(id = "choosePhoto"),
    val name: TextFieldState = TextFieldState(
        id = "name",
        type = TextInputType.NAME_SURNAME,
        enabled = false
    ),
    val email: TextFieldState = TextFieldState(
        id = "email",
        type = TextInputType.EMAIL,
        enabled = false
    ),
    val phone: TextFieldState = TextFieldState(
        id = "phone",
        type = TextInputType.PHONE_NUMBER,
        enabled = false
    ),
    val birthday: TextFieldState = TextFieldState(
        id = "birthday",
        type = TextInputType.DATE, enabled = false
    ),
    val screen: StateColumnState = StateColumnState(),
    val allInterests: List<Interest> = emptyList(),
    val changedInterests: List<Interest> = emptyList(),
    val nickName: TextFieldState = TextFieldState(id = "nickName"),
    val about: TextFieldState = TextFieldState(id = "about"),
    val editMode: Boolean = false
)