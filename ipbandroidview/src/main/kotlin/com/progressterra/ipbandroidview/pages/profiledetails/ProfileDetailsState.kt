package com.progressterra.ipbandroidview.pages.profiledetails

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.authprofile.AuthProfileState
import com.progressterra.ipbandroidview.features.editbutton.EditButtonState
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState

@Immutable
data class ProfileDetailsState(
    val editUser: EditUserState = EditUserState(),
    val editButton: EditButtonState = EditButtonState(),
    val authProfileState: AuthProfileState = AuthProfileState()
) {

    fun updateEditUser(newEditUser: EditUserState) = copy(editUser = newEditUser)

    fun startCancelEdit() = copy(editButton = editButton.startCancel())

    fun updateName(name: String) = copy(editUser = editUser.updateName(name))

    fun updateEmail(email: String) = copy(editUser = editUser.updateEmail(email))

    fun updatePhone(phone: String) = copy(editUser = editUser.updatePhone(phone))

    fun updateBirthday(birthday: String) = copy(editUser = editUser.updateBirthday(birthday))

    fun updateCitizenship(citizenship: String) =
        copy(editUser = editUser.updateCitizenship(citizenship))

    fun updateAddress(address: String) = copy(editUser = editUser.updateAddress(address))

    fun updatePassport(passport: String) = copy(editUser = editUser.updatePassport(passport))

    fun updatePassportProvider(passportProvider: String) =
        copy(editUser = editUser.updatePassportProvider(passportProvider))

    fun updatePassportProviderCode(passportProviderCode: String) =
        copy(editUser = editUser.updatePassportProviderCode(passportProviderCode))

    fun updatePatent(patent: String) = copy(editUser = editUser.updatePatent(patent))

    fun updateSaveEnabled(enabled: Boolean) =
        copy(editButton = editButton.updateSaveEnabled(enabled))
}
