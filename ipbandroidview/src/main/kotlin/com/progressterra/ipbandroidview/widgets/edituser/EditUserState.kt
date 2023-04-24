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
    val passportProviderCode: TextFieldState = TextFieldState(id = "passportProviderCode"),
    val patent: TextFieldState = TextFieldState(id = "patent")
) {

    fun updateName(newName: String) = copy(name = name.updateText(newName))

    fun updateEmail(newEmail: String) = copy(email = email.updateText(newEmail))

    fun updateEmailEnabled(enabled: Boolean) = copy(email = email.updateEnabled(enabled))

    fun updateNameEnabled(enabled: Boolean) = copy(name = name.updateEnabled(enabled))

    fun updatePhone(newPhone: String) = copy(phone = phone.updateText(newPhone))

    fun updatePhoneEnabled(enabled: Boolean) = copy(phone = phone.updateEnabled(enabled))

    fun updateBirthday(newBirthday: String) = copy(birthday = birthday.updateText(newBirthday))

    fun updateBirthdayEnabled(enabled: Boolean) = copy(birthday = birthday.updateEnabled(enabled))

    fun updateCitizenship(newCitizenship: String) =
        copy(citizenship = citizenship.updateText(newCitizenship))

    fun updateCitizenshipEnabled(enabled: Boolean) =
        copy(citizenship = citizenship.updateEnabled(enabled))

    fun updateAddress(newAddress: String) = copy(address = address.updateText(newAddress))

    fun updateAddressEnabled(enabled: Boolean) = copy(address = address.updateEnabled(enabled))

    fun updatePassport(newPassport: String) = copy(passport = passport.updateText(newPassport))

    fun updatePassportEnabled(enabled: Boolean) = copy(passport = passport.updateEnabled(enabled))

    fun updatePassportProvider(newPassportProvider: String) =
        copy(passportProvider = passportProvider.updateText(newPassportProvider))

    fun updatePassportProviderEnabled(enabled: Boolean) =
        copy(passportProvider = passportProvider.updateEnabled(enabled))


    fun updatePassportProviderCode(newPassportProviderCode: String) =
        copy(passportProviderCode = passportProviderCode.updateText(newPassportProviderCode))

    fun updatePassportProviderCodeEnabled(enabled: Boolean) =
        copy(passportProviderCode = passportProviderCode.updateEnabled(enabled))

    fun updatePatent(newPatent: String) = copy(patent = patent.updateText(newPatent))

    fun updatePatentEnabled(enabled: Boolean) = copy(patent = patent.updateEnabled(enabled))
}
