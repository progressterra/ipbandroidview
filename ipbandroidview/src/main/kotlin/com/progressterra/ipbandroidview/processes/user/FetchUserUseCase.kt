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
                .uNameText(buildString {
                    if (UserData.userName.name.isNotBlank()) append(UserData.userName.name)
                    if (UserData.userName.surname.isNotBlank()) append(" ${UserData.userName.surname}")
                })
                .uEmailText(UserData.email)
                .uPhone(UserData.phone)
                .uBirthday(
                    Instant.ofEpochMilli(UserData.dateOfBirthday).atZone(
                        ZoneId.systemDefault()
                    ).toLocalDate().print()
                )
                .uCitizenshipText(UserData.citizenship)
                .uAddressText(UserData.address.printAddress())
                .uPassportText(UserData.passport)
                .uPassportProviderText(UserData.passportProvider)
                .uPassportCodeText(UserData.passportProviderCode)
                .uPatentText(UserData.patent)
                .uPhoneEnabled(false)
                .uPassportProviderEnabled(false)
                .uPassportEnabled(false)
                .uPassportCodeEnabled(false)
                .uCitizenshipEnabled(false)
                .uEmailEnabled(false)
                .uNameEnabled(false)
                .uBirthdayEnabled(false)
                .uAddressEnabled(false)
                .uPatentEnabled(false)
        }
    }
}