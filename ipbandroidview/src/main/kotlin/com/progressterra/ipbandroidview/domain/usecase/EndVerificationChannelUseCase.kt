package com.progressterra.ipbandroidview.domain.usecase

import android.os.Build
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.IncomeDataForEndLogin
import com.progressterra.ipbandroidapi.user.UserData

interface EndVerificationChannelUseCase {

    suspend fun end(phoneNumber: String, code: String): Result<Unit>

    class Base(
        private val repo: SCRMRepository
    ) : EndVerificationChannelUseCase {

        override suspend fun end(phoneNumber: String, code: String): Result<Unit> = runCatching {
            val result = repo.verificationChannelEnd(
                IncomeDataForEndLogin(
                    channelType = 0,
                    channelData = phoneNumber,
                    codeConfirm = code,
                    infoDevice = "SDK: ${Build.VERSION.SDK_INT} Model: ${Build.MODEL}"
                )
            ).getOrThrow()
            UserData.deviceId = result?.idDevice!!
            UserData.phone = phoneNumber
            UserData.clientExist = true
        }
    }
}