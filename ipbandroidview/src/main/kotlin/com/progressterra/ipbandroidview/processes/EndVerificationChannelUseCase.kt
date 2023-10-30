package com.progressterra.ipbandroidview.processes

import android.os.Build
import com.progressterra.ipbandroidapi.api.auth.AuthService
import com.progressterra.ipbandroidapi.api.auth.models.IncomeDataEndChannelVerificationForAT
import com.progressterra.ipbandroidapi.api.auth.models.StatusResult
import com.progressterra.ipbandroidapi.api.scrm.ScrmService
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Sex
import com.progressterra.ipbandroidview.entities.formatZdtIso
import com.progressterra.ipbandroidview.entities.parseToZDT
import com.progressterra.ipbandroidview.entities.toSex
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.UserName

interface EndVerificationChannelUseCase {

    suspend operator fun invoke(tempToken: String, phoneNumber: String, code: String): Result<Unit>

    class Base(
        private val authService: AuthService,
        private val scrmService: ScrmService,
        obtainAccessToken: ObtainAccessToken, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : EndVerificationChannelUseCase, AbstractTokenUseCase(
        obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

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
            if (result.result?.status != StatusResult.SUCCESS) throw ToastedException(R.string.wrong_code)
            UserData.deviceId = result.data?.idDevice!!
            UserData.phone = formattedPhoneNumber
            UserData.clientExist = true
            val info = withToken { token -> scrmService.getClient(token) }.getOrThrow().data!!
            UserData.sex = when (info.sex?.toSex()) {
                Sex.MALE -> 1
                Sex.FEMALE -> 2
                null -> 0
            }
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