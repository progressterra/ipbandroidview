package com.progressterra.ipbandroidview.domain

import android.os.Build
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.VerificationType
import com.progressterra.ipbandroidapi.user.UserData

interface EndVerificationChannelUseCase {

    suspend fun end(phoneNumber: String, code: String): Result<Unit>

    class Base(
        private val repo: SCRMRepository
    ) : EndVerificationChannelUseCase {

        override suspend fun end(phoneNumber: String, code: String): Result<Unit> = runCatching {
            val result = repo.finishVerificationChannel(
                VerificationType.PHONE,
                phoneNumber,
                code,
                "SDK: ${Build.VERSION.SDK_INT} Model: ${Build.MODEL}"
            ).getOrThrow()
            UserData.deviceId = result
            UserData.clientExist = true
        }
    }
}