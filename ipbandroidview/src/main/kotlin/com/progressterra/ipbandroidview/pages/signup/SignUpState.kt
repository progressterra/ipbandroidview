package com.progressterra.ipbandroidview.pages.signup

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.authorskip.AuthOrSkipState
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState

@Immutable
data class SignUpState(
    val editUser: EditUserState = EditUserState(),
    val authOrSkip: AuthOrSkipState = AuthOrSkipState()
) {

    fun updateName(name: String): SignUpState = copy(editUser = editUser.updateName(name))

    fun updateEmail(email: String): SignUpState = copy(editUser = editUser.updateEmail(email))

    fun updatePhone(phone: String): SignUpState = copy(editUser = editUser.updatePhone(phone))

    fun updateBirthday(birthday: String): SignUpState =
        copy(editUser = editUser.updateBirthday(birthday))

    fun updateCitizenship(citizenship: String): SignUpState =
        copy(editUser = editUser.updateCitizenship(citizenship))

    fun updateAddress(address: String): SignUpState =
        copy(editUser = editUser.updateAddress(address))

    fun updatePassport(passport: String): SignUpState =
        copy(editUser = editUser.updatePassport(passport))

    fun updatePassportProvider(passportProvider: String): SignUpState =
        copy(editUser = editUser.updatePassportProvider(passportProvider))

    fun updatePassportProviderCode(passportProviderCode: String): SignUpState =
        copy(editUser = editUser.updatePassportProviderCode(passportProviderCode))

    fun updatePatent(patent: String): SignUpState = copy(editUser = editUser.updatePatent(patent))

    fun updateAuthEnabled(enabled: Boolean): SignUpState =
        copy(authOrSkip = authOrSkip.updateAuthButton(enabled))
}