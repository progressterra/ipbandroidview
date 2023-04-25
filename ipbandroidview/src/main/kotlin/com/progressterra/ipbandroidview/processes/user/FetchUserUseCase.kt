package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.print
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState
import com.progressterra.ipbandroidview.widgets.edituser.uAddressEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uAddressText
import com.progressterra.ipbandroidview.widgets.edituser.uBirthdayEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uBirthdayText
import com.progressterra.ipbandroidview.widgets.edituser.uCitizenshipEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uCitizenshipText
import com.progressterra.ipbandroidview.widgets.edituser.uEmailEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uEmailText
import com.progressterra.ipbandroidview.widgets.edituser.uNameEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uNameText
import com.progressterra.ipbandroidview.widgets.edituser.uPassportCodeEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uPassportCodeText
import com.progressterra.ipbandroidview.widgets.edituser.uPassportEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uPassportProviderEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uPassportProviderText
import com.progressterra.ipbandroidview.widgets.edituser.uPassportText
import com.progressterra.ipbandroidview.widgets.edituser.uPatentEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uPatentText
import com.progressterra.ipbandroidview.widgets.edituser.uPhoneEnabled
import com.progressterra.ipbandroidview.widgets.edituser.uPhoneText
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
                .uPhoneText(UserData.phone)
                .uBirthdayText(
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