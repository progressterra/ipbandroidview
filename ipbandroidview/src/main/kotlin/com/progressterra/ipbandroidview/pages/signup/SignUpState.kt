package com.progressterra.ipbandroidview.pages.signup

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.authorskip.AuthOrSkipState
import com.progressterra.ipbandroidview.features.authorskip.uAuthEnabled
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState
import com.progressterra.ipbandroidview.widgets.edituser.uBirthdayText
import com.progressterra.ipbandroidview.widgets.edituser.uEmailText
import com.progressterra.ipbandroidview.widgets.edituser.uNameText
import com.progressterra.ipbandroidview.widgets.edituser.uPhoneEnabled

@Immutable
data class SignUpState(
    val editUser: EditUserState = EditUserState(),
    val authOrSkip: AuthOrSkipState = AuthOrSkipState(),
    val screenState: ScreenState = ScreenState.LOADING
) {

    fun uScreenState(newScreenState: ScreenState) = copy(screenState = newScreenState)

    fun uEditUser(newEditUser: EditUserState) = copy(editUser = newEditUser)

    fun uName(name: String) = copy(editUser = editUser.uNameText(name))

    fun uEmail(email: String) = copy(editUser = editUser.uEmailText(email))

    fun uPhoneEnabled(enabled: Boolean) = copy(editUser = editUser.uPhoneEnabled(enabled))

    fun uAuthEnabled(enabled: Boolean) = copy(authOrSkip = authOrSkip.uAuthEnabled(enabled))

    fun uBirthday(birthday: String) = copy(editUser = editUser.uBirthdayText(birthday))
}
