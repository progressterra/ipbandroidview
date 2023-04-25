package com.progressterra.ipbandroidview.pages.signup

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.authorskip.AuthOrSkipState
import com.progressterra.ipbandroidview.features.authorskip.uAuthEnabled
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState
import com.progressterra.ipbandroidview.widgets.edituser.uAddressEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uAddressText
import com.progressterra.ipbandroidview.widgets.edituser.uBirthdayEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uBirthdayText
import com.progressterra.ipbandroidview.widgets.edituser.uCitizenshipEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uCitizenshipText
import com.progressterra.ipbandroidview.widgets.edituser.uEmailEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uEmailText
import com.progressterra.ipbandroidview.widgets.edituser.uNameEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uNameText
import com.progressterra.ipbandroidview.widgets.edituser.uPassportCodeEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uPassportCodeText
import com.progressterra.ipbandroidview.widgets.edituser.uPassportEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uPassportProviderEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uPassportProviderText
import com.progressterra.ipbandroidview.widgets.edituser.uPassportText
import com.progressterra.ipbandroidview.widgets.edituser.uPatentEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uPatentText
import com.progressterra.ipbandroidview.widgets.edituser.uPhoneEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uPhoneText

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

    fun uPhone(phone: String) = copy(editUser = editUser.uPhoneText(phone))

    fun uPhoneEnabled(enabled: Boolean) = copy(editUser = editUser.uPhoneEnabled(enabled))

    fun uAuthEnabled(enabled: Boolean) = copy(authOrSkip = authOrSkip.uAuthEnabled(enabled))

    fun uBirthday(birthday: String) = copy(editUser = editUser.uBirthdayText(birthday))

    fun uCitizenship(citizenship: String) = copy(editUser = editUser.uCitizenshipText(citizenship))

    fun uAddress(address: String) = copy(editUser = editUser.uAddressText(address))

    fun uPassport(passport: String) = copy(editUser = editUser.uPassportText(passport))

    fun uPassportProvider(passportProvider: String) =
        copy(editUser = editUser.uPassportProviderText(passportProvider))

    fun uPassportProviderCode(passportProviderCode: String) =
        copy(editUser = editUser.uPassportCodeText(passportProviderCode))

    fun uPatent(patent: String) = copy(editUser = editUser.uPatentText(patent))

    fun uEmailEnabled(enabled: Boolean) = copy(editUser = editUser.uEmailEnabled(enabled))

    fun uNameEnabled(enabled: Boolean) = copy(editUser = editUser.uNameEnabled(enabled))

    fun uBirthdayEnabled(enabled: Boolean) = copy(editUser = editUser.uBirthdayEnabled(enabled))


    fun uCitizenshipEnabled(enabled: Boolean) =
        copy(editUser = editUser.uCitizenshipEnabled(enabled))


    fun uAddressEnabled(enabled: Boolean) = copy(editUser = editUser.uAddressEnabled(enabled))


    fun uPassportEnabled(enabled: Boolean) = copy(editUser = editUser.uPassportEnabled(enabled))


    fun uPassportProviderEnabled(enabled: Boolean) =
        copy(editUser = editUser.uPassportProviderEnabled(enabled))

    fun uPassportProviderCodeEnabled(enabled: Boolean) =
        copy(editUser = editUser.uPassportCodeEnabled(enabled))


    fun uPatentEnabled(enabled: Boolean) = copy(editUser = editUser.uPatentEnabled(enabled))
}
