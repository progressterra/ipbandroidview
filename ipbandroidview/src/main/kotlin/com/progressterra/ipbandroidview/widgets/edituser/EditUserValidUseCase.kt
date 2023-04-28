package com.progressterra.ipbandroidview.widgets.edituser

import com.progressterra.ipbandroidview.shared.isAddress
import com.progressterra.ipbandroidview.shared.isBirthday
import com.progressterra.ipbandroidview.shared.isEmail
import com.progressterra.ipbandroidview.shared.isNameAndSurname
import com.progressterra.ipbandroidview.shared.isRussianPhoneNumber
import com.progressterra.ipbandroidview.shared.isTestPhoneNumber
import com.progressterra.ipbandroidview.shared.isValidCitizenship

interface EditUserValidUseCase {

    suspend operator fun invoke(editUserState: EditUserState): Result<Unit>

    class Base : EditUserValidUseCase {

        override suspend fun invoke(editUserState: EditUserState): Result<Unit> = runCatching {
            val valid =
                editUserState.name.text.isNameAndSurname() &&
                        editUserState.email.text.isEmail() &&
                        (editUserState.phone.text.isRussianPhoneNumber() || editUserState.phone.text.isTestPhoneNumber()) &&
                        editUserState.birthday.text.isBirthday() &&
                        editUserState.citizenship.text.isValidCitizenship() &&
                        editUserState.address.text.isAddress() &&
                        editUserState.passport.text.isNotBlank() &&
                        editUserState.passportProvider.text.isNotBlank() &&
                        editUserState.passportCode.text.isNotBlank() &&
                        editUserState.patent.text.isNotBlank() &&
                        editUserState.makePhoto.items.isNotEmpty()
            if (!valid) {
                throw Exception("Invalid")
            }
        }
    }
}