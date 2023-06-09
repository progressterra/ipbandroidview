package com.progressterra.ipbandroidview.pages.profiledetails

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.authprofile.AuthProfileState
import com.progressterra.ipbandroidview.features.editbutton.EditButtonState
import com.progressterra.ipbandroidview.features.editbutton.uSaveEnabled
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState
import com.progressterra.ipbandroidview.widgets.edituser.uBirthdayEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uBirthdayText
import com.progressterra.ipbandroidview.widgets.edituser.uEmailEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uEmailText
import com.progressterra.ipbandroidview.widgets.edituser.uNameEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uNameText
import com.progressterra.ipbandroidview.widgets.edituser.uPhoneEnabled

@Immutable
data class ProfileDetailsState(
    val editUser: EditUserState = EditUserState(),
    val editButton: EditButtonState = EditButtonState(),
    val authProfileState: AuthProfileState = AuthProfileState(),
    val screen: ScreenState = ScreenState.LOADING
) {

    fun uPhoneEnabled(newEnabled: Boolean) = copy(editUser = editUser.uPhoneEnabled(newEnabled))

    fun uScreenState(newScreenState: ScreenState) = copy(screen = newScreenState)

    fun uEditUser(newEditUser: EditUserState) = copy(editUser = newEditUser)

    fun startCancelEdit() = copy(editButton = editButton.startCancel())

    fun uName(name: String) = copy(editUser = editUser.uNameText(name))

    fun uEmail(email: String) = copy(editUser = editUser.uEmailText(email))

    fun uBirthday(birthday: String) = copy(editUser = editUser.uBirthdayText(birthday))

    fun uSaveEnabled(enabled: Boolean) = copy(editButton = editButton.uSaveEnabled(enabled))

    fun uEmailEnabled(enabled: Boolean) = copy(editUser = editUser.uEmailEnabled(enabled))

    fun uNameEnabled(enabled: Boolean) = copy(editUser = editUser.uNameEnabled(enabled))

    fun uBirthdayEnabled(enabled: Boolean) = copy(editUser = editUser.uBirthdayEnabled(enabled))
}
