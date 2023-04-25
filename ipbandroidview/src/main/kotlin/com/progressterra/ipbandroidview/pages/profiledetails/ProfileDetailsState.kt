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

    fun uEditUser(newEditUser: EditUserState) = copy(editUser = newEditUser)

    fun startCancelEdit() = copy(editButton = editButton.startCancel())

    fun uName(name: String) = copy(editUser = editUser.uNameText(name))

    fun uEmail(email: String) = copy(editUser = editUser.uEmailText(email))

    fun uPhone(phone: String) = copy(editUser = editUser.uPhone(phone))

    fun uBirthday(birthday: String) = copy(editUser = editUser.uBirthday(birthday))

    fun uCitizenship(citizenship: String) =
        copy(editUser = editUser.uCitizenshipText(citizenship))

    fun uAddress(address: String) = copy(editUser = editUser.uAddressText(address))

    fun uPassport(passport: String) = copy(editUser = editUser.uPassportText(passport))

    fun uPassportProvider(passportProvider: String) =
        copy(editUser = editUser.uPassportProviderText(passportProvider))

    fun uPassportProviderCode(passportProviderCode: String) =
        copy(editUser = editUser.uPassportCodeText(passportProviderCode))

    fun uPatent(patent: String) = copy(editUser = editUser.uPatentText(patent))

    fun uSaveEnabled(enabled: Boolean) =
        copy(editButton = editButton.uSaveEnabled(enabled))

    fun uEmailEnabled(enabled: Boolean) = copy(editUser = editUser.uEmailEnabled(enabled))

    fun uNameEnabled(enabled: Boolean) = copy(editUser = editUser.uNameEnabled(enabled))

    fun uBirthdayEnabled(enabled: Boolean) =
        copy(editUser = editUser.uBirthdayEnabled(enabled))


    fun uCitizenshipEnabled(enabled: Boolean) =
        copy(editUser = editUser.uCitizenshipEnabled(enabled))


    fun uAddressEnabled(enabled: Boolean) =
        copy(editUser = editUser.uAddressEnabled(enabled))


    fun uPassportEnabled(enabled: Boolean) =
        copy(editUser = editUser.uPassportEnabled(enabled))


    fun uPassportProviderEnabled(enabled: Boolean) =
        copy(editUser = editUser.uPassportProviderEnabled(enabled))

    fun uPassportProviderCodeEnabled(enabled: Boolean) =
        copy(editUser = editUser.uPassportCodeEnabled(enabled))


    fun uPatentEnabled(enabled: Boolean) =
        copy(editUser = editUser.uPatentEnabled(enabled))
}
