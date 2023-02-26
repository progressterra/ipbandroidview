package com.progressterra.ipbandroidview.domain.usecase.user

import android.os.Build
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.IncomeDataForEndLogin
import com.progressterra.ipbandroidview.data.UserData

interface EndVerificationChannelUseCase {

    suspend operator fun invoke(phoneNumber: String, code: String): Result<Unit>

    class Base(
        private val repo: SCRMRepository
    ) : EndVerificationChannelUseCase {

        override suspend fun invoke(phoneNumber: String, code: String): Result<Unit> = runCatching {
            var formattedPhoneNumber = phoneNumber.trim()
            if (formattedPhoneNumber.startsWith('8'))
                formattedPhoneNumber = formattedPhoneNumber.replaceFirst('8', '7')
            val result = repo.verificationChannelEnd(
                IncomeDataForEndLogin(
                    channelType = 0,
                    channelData = formattedPhoneNumber,
                    codeConfirm = code,
                    infoDevice = "SDK: ${Build.VERSION.SDK_INT} Model: ${Build.MODEL}"
                )
            ).getOrThrow()
            UserData.deviceId = result?.idDevice!!
            UserData.phone = formattedPhoneNumber
            UserData.clientExist = true
        }
    }
}