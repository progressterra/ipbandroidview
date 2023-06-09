package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.print
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState
import com.progressterra.ipbandroidview.widgets.edituser.uBirthdayText
import com.progressterra.ipbandroidview.widgets.edituser.uEmailText
import com.progressterra.ipbandroidview.widgets.edituser.uNameText
import com.progressterra.ipbandroidview.widgets.edituser.uPhoneText
import java.util.Date

interface FetchUserUseCase {

    suspend operator fun invoke(): Result<EditUserState>

    class Base : FetchUserUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(): Result<EditUserState> = handle {
            EditUserState()
                .uNameText(buildString {
                    if (UserData.userName.name.isNotBlank()) append(UserData.userName.name)
                    if (UserData.userName.surname.isNotBlank()) append(" ${UserData.userName.surname}")
                })
                .uEmailText(UserData.email)
                .uPhoneText(UserData.phone)
                .uBirthdayText(Date(UserData.dateOfBirthday).print())
        }
    }
}