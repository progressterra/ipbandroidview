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

    fun updatePhone(newPhone: String) = copy(phone = phone.updateText(newPhone))

    fun updateBirthday(newBirthday: String) = copy(birthday = birthday.updateText(newBirthday))

    fun updateCitizenship(newCitizenship: String) =
        copy(citizenship = citizenship.updateText(newCitizenship))

    fun updateAddress(newAddress: String) = copy(address = address.updateText(newAddress))

    fun updatePassport(newPassport: String) = copy(passport = passport.updateText(newPassport))

    fun updatePassportProvider(newPassportProvider: String) =
        copy(passportProvider = passportProvider.updateText(newPassportProvider))

    fun updatePassportProviderCode(newPassportProviderCode: String) =
        copy(passportProviderCode = passportProviderCode.updateText(newPassportProviderCode))

    fun updatePatent(newPatent: String) = copy(patent = patent.updateText(newPatent))
}