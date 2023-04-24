package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.print
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState
import java.time.Instant
import java.time.ZoneId

interface FetchUserUseCase {

    suspend operator fun invoke(): Result<EditUserState>

    class Base : FetchUserUseCase {

        override suspend fun invoke(): Result<EditUserState> = runCatching {
            EditUserState(
                name = TextFieldState(text = buildString {
                    if (UserData.userName.name.isNotBlank()) append(UserData.userName.name)
                    if (UserData.userName.surname.isNotBlank()) append(" ${UserData.userName.surname}")
                }),
                email = TextFieldState(text = UserData.email),
                phone = TextFieldState(text = UserData.phone),
                birthday = TextFieldState(
                    text = Instant.ofEpochMilli(UserData.dateOfBirthday).atZone(
                        ZoneId.systemDefault()
                    ).toLocalDate().print()
                ),
                citizenship = TextFieldState(text = UserData.citizenship),
                address = TextFieldState(text = UserData.address.printAddress()),
                passport = TextFieldState(text = UserData.passport),
                passportProvider = TextFieldState(text = UserData.passportProvider),
                passportProviderCode = TextFieldState(text = UserData.passportProviderCode),
                patent = TextFieldState(text = UserData.patent)
            )
        }
    }
}