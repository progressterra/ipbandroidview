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

    fun uScreenState(newScreenState: ScreenState) = copy(screenState = newScreenState)

    fun uName(name: String) = copy(editUser = editUser.uNameText(name))

    fun uEmail(email: String) = copy(editUser = editUser.uEmailText(email))

    fun uPhone(phone: String) = copy(editUser = editUser.uPhone(phone))

    fun uPhoneEnabled(enabled: Boolean) = copy(editUser = editUser.uPhoneEnabled(enabled))

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

    fun uAuthEnabled(enabled: Boolean) =
        copy(authOrSkip = authOrSkip.uAuthEnabled(enabled))
}
