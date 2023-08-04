package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidview.entities.formatZDT
import com.progressterra.ipbandroidview.entities.parseToZDT
import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState

interface FetchUserUseCase {

    suspend operator fun invoke(): Result<EditUserState>

    class Base : FetchUserUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(): Result<EditUserState> = handle {
            val editUser = EditUserState()
            editUser.copy(
                name = editUser.name.unFormatByType(buildString {
                    if (UserData.userName.name.isNotBlank()) append(UserData.userName.name)
                    if (UserData.userName.surname.isNotBlank()) append(" ${UserData.userName.surname}")
                }),
                email = editUser.email.unFormatByType(UserData.email),
                phone = editUser.phone.unFormatByType(UserData.phone),
                birthday = editUser.birthday.unFormatByType(
                    UserData.dateOfBirthday.parseToZDT().formatZDT("dd.MM.yyyy")
                )
            )
        }
    }
}