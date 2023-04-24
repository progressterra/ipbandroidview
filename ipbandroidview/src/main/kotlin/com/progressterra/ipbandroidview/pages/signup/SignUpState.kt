package com.progressterra.ipbandroidview.pages.signup

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.authorskip.AuthOrSkipState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState

@Immutable
data class SignUpState(
    val editUser: EditUserState = EditUserState(),
    val authOrSkip: AuthOrSkipState = AuthOrSkipState(),
    val screenState: ScreenState = ScreenState.LOADING
) {

    fun updateScreenState(newScreenState: ScreenState) = copy(screenState = newScreenState)

    fun updateName(name: String) = copy(editUser = editUser.updateName(name))

    fun updateEmail(email: String) = copy(editUser = editUser.updateEmail(email))

    fun updatePhone(phone: String) = copy(editUser = editUser.updatePhone(phone))

    fun updatePhoneEnabled(enabled: Boolean) = copy(editUser = editUser.updatePhoneEnabled(enabled))

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

    fun updateAuthEnabled(enabled: Boolean) =
        copy(authOrSkip = authOrSkip.updateAuthButton(enabled))
}
