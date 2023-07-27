package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.print
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState
import java.util.Date

interface FetchUserUseCase {

    suspend operator fun invoke(): Result<EditUserState>

    class Base : FetchUserUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(): Result<EditUserState> = handle {
            val editUser = EditUserState()
            editUser.copy(
                name = editUser.name.copy(text = buildString {
                    if (UserData.userName.name.isNotBlank()) append(UserData.userName.name)
                    if (UserData.userName.surname.isNotBlank()) append(" ${UserData.userName.surname}")
                }),
                email = editUser.email.copy(text = UserData.email),
                phone = editUser.phone.copy(text = UserData.phone),
                birthday = editUser.birthday.copy(text = Date(UserData.dateOfBirthday).print())
            )
        }
    }
}