package com.progressterra.ipbandroidview.widgets.edituser

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

@Immutable
data class EditUserState(
    val name: TextFieldState = TextFieldState(id = "name"),
    val email: TextFieldState = TextFieldState(id = "email"),
    val phone: TextFieldState = TextFieldState(id = "phone"),
    val birthday: TextFieldState = TextFieldState(id = "birthday"),
    val citizenship: TextFieldState = TextFieldState(id = "citizenship"),
    val address: TextFieldState = TextFieldState(id = "address"),
    val passport: TextFieldState = TextFieldState(id = "passport"),
    val passportProvider: TextFieldState = TextFieldState(id = "passportProvider"),
    val passportCode: TextFieldState = TextFieldState(id = "passportProviderCode"),
    val patent: TextFieldState = TextFieldState(id = "patent")
) {

    fun uNameText(newName: String) = copy(name = name.uText(newName))

    fun uNameEnabled(enabled: Boolean) = copy(name = name.uEnabled(enabled))

    fun uEmailText(newEmail: String) = copy(email = email.uText(newEmail))

    fun uEmailEnabled(enabled: Boolean) = copy(email = email.uEnabled(enabled))


    fun uPhone(newPhone: String) = copy(phone = phone.uText(newPhone))

    fun uPhoneEnabled(enabled: Boolean) = copy(phone = phone.uEnabled(enabled))

    fun uBirthday(newBirthday: String) = copy(birthday = birthday.uText(newBirthday))

    fun uBirthdayEnabled(enabled: Boolean) = copy(birthday = birthday.uEnabled(enabled))

    fun uCitizenshipText(newCitizenship: String) =
        copy(citizenship = citizenship.uText(newCitizenship))

    fun uCitizenshipEnabled(enabled: Boolean) =
        copy(citizenship = citizenship.uEnabled(enabled))

    fun uAddressText(newAddress: String) = copy(address = address.uText(newAddress))

    fun uAddressEnabled(enabled: Boolean) = copy(address = address.uEnabled(enabled))

    fun uPassportText(newPassport: String) = copy(passport = passport.uText(newPassport))

    fun uPassportEnabled(enabled: Boolean) = copy(passport = passport.uEnabled(enabled))

    fun uPassportProviderText(newPassportProvider: String) =
        copy(passportProvider = passportProvider.uText(newPassportProvider))

    fun uPassportProviderEnabled(enabled: Boolean) =
        copy(passportProvider = passportProvider.uEnabled(enabled))


    fun uPassportCodeText(newPassportCode: String) =
        copy(passportCode = passportCode.uText(newPassportCode))

    fun uPassportCodeEnabled(enabled: Boolean) =
        copy(passportCode = passportCode.uEnabled(enabled))

    fun uPatentText(newPatent: String) = copy(patent = patent.uText(newPatent))

    fun uPatentEnabled(enabled: Boolean) = copy(patent = patent.uEnabled(enabled))
}
