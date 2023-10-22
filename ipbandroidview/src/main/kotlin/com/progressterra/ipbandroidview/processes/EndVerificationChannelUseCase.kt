package com.progressterra.ipbandroidview.processes

import android.os.Build
import com.progressterra.ipbandroidapi.api.auth.AuthService
import com.progressterra.ipbandroidapi.api.auth.models.IncomeDataEndChannelVerificationForAT
import com.progressterra.ipbandroidapi.api.scrm.ScrmService
import com.progressterra.ipbandroidview.entities.formatZdtIso
import com.progressterra.ipbandroidview.entities.parseToZDT
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.UserName

interface EndVerificationChannelUseCase {

    suspend operator fun invoke(tempToken: String, phoneNumber: String, code: String): Result<Unit>

    class Base(
        private val authService: AuthService,
        private val scrmService: ScrmService,
        obtainAccessToken: ObtainAccessToken
    ) : EndVerificationChannelUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(
            tempToken: String,
            phoneNumber: String,
            code: String
        ): Result<Unit> = handle {
            var formattedPhoneNumber = phoneNumber.trim()
            if (formattedPhoneNumber.startsWith('8')) formattedPhoneNumber =
                formattedPhoneNumber.replaceFirst('8', '7')
            val result = authService.loginEnd(
                IncomeDataEndChannelVerificationForAT(
                    tempToken = tempToken,
                    codeFromSMS = code,
                    infoDevice = "SDK: ${Build.VERSION.SDK_INT} Model: ${Build.MODEL}"
                )
            )
            UserData.deviceId = result.data?.idDevice!!
            UserData.phone = formattedPhoneNumber
            UserData.clientExist = true
            val info = withToken { token -> scrmService.getClient(token) }.getOrThrow().data!!
            UserData.idUnique = info.idUnique!!
            UserData.userName = UserName(
                name = info.name ?: "",
                surname = info.soname ?: "",
                patronymic = info.patronymic ?: ""
            )
            UserData.dateOfBirthday = info.dateOfBirth?.parseToZDT()?.formatZdtIso() ?: ""
            UserData.email = info.eMailGeneral ?: ""
        }
    }
}