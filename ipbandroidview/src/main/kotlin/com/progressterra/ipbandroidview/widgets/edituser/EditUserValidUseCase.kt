package com.progressterra.ipbandroidview.widgets.edituser

import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.shared.isBirthday
import com.progressterra.ipbandroidview.shared.isEmail
import com.progressterra.ipbandroidview.shared.isNameAndSurname
import com.progressterra.ipbandroidview.shared.isRussianPhoneNumber
import com.progressterra.ipbandroidview.shared.isTestPhoneNumber

interface EditUserValidUseCase {

    suspend operator fun invoke(editUserState: EditUserState): Result<Unit>

    class Base(
        private val citizenshipSuggestionsUseCase: CitizenshipSuggestionsUseCase
    ) : EditUserValidUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(editUserState: EditUserState): Result<Unit> = handle {
            val valid =
                editUserState.name.text.isNameAndSurname() &&
                        editUserState.email.text.isEmail() &&
                        (editUserState.phone.text.isRussianPhoneNumber() || editUserState.phone.text.isTestPhoneNumber()) &&
                        editUserState.birthday.text.isBirthday() &&
                        citizenshipSuggestionsUseCase(editUserState.citizenship.text).items.any { it.name == editUserState.citizenship.text }
            if (!valid) {
                throw Exception("Invalid entries")
            }
        }
    }
}