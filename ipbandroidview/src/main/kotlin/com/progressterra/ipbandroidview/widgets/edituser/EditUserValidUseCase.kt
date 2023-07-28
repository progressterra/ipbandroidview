package com.progressterra.ipbandroidview.widgets.edituser

import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.shared.isDate
import com.progressterra.ipbandroidview.shared.isEmail
import com.progressterra.ipbandroidview.shared.isNameAndSurname
import com.progressterra.ipbandroidview.shared.isRussianPhoneNumber

interface EditUserValidUseCase {

    suspend operator fun invoke(editUserState: EditUserState): Result<Unit>

    class Base : EditUserValidUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(editUserState: EditUserState): Result<Unit> = handle {
            val valid =
                editUserState.name.formatByType().isNameAndSurname() &&
                        editUserState.email.formatByType().isEmail() &&
                        editUserState.phone.formatByType().isRussianPhoneNumber() &&
                        editUserState.birthday.formatByType().isDate()
            if (!valid) {
                throw Exception("Invalid entries")
            }
        }
    }
}