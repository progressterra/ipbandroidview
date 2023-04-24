package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.print
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState
import java.time.Instant
import java.time.ZoneId

interface FetchUserUseCase {

    suspend operator fun invoke(): Result<EditUserState>

    class Base : FetchUserUseCase {

        override suspend fun invoke(): Result<EditUserState> = runCatching {
            EditUserState()
                .updateName(buildString {
                    if (UserData.userName.name.isNotBlank()) append(UserData.userName.name)
                    if (UserData.userName.surname.isNotBlank()) append(" ${UserData.userName.surname}")
                })
                .updateEmail(UserData.email)
                .updatePhone(UserData.phone)
                .updateBirthday(
                    Instant.ofEpochMilli(UserData.dateOfBirthday).atZone(
                        ZoneId.systemDefault()
                    ).toLocalDate().print()
                )
                .updateCitizenship(UserData.citizenship)
                .updateAddress(UserData.address.printAddress())
                .updatePassport(UserData.passport)
                .updatePassportProvider(UserData.passportProvider)
                .updatePassportProviderCode(UserData.passportProviderCode)
                .updatePatent(UserData.patent)
                .updatePhoneEnabled(false)
                .updatePassportProviderEnabled(false)
                .updatePassportEnabled(false)
                .updatePassportProviderCodeEnabled(false)
                .updateCitizenshipEnabled(false)
                .updateEmailEnabled(false)
                .updateNameEnabled(false)
                .updateBirthdayEnabled(false)
                .updateAddressEnabled(false)
                .updatePatentEnabled(false)
        }
    }
}