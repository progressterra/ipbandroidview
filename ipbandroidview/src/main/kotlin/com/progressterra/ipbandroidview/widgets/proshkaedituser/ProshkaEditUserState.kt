package com.progressterra.ipbandroidview.widgets.proshkaedituser

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

@Immutable
data class ProshkaEditUserState(
    val name: TextFieldState = TextFieldState(),
    val email: TextFieldState = TextFieldState(),
    val phone: TextFieldState = TextFieldState(),
    val birthday: TextFieldState = TextFieldState(),
    val citizenship: TextFieldState = TextFieldState(),
    val address: TextFieldState = TextFieldState(),
    val passport: TextFieldState = TextFieldState(),
    val passportProvider: TextFieldState = TextFieldState(),
    val passportProviderCode: TextFieldState = TextFieldState(),
    val patent: TextFieldState = TextFieldState()
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