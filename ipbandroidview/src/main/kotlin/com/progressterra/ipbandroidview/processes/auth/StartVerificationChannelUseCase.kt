package com.progressterra.ipbandroidview.processes.auth

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.IncomeChannelData
import com.progressterra.ipbandroidview.ext.throwOnFailure

interface StartVerificationChannelUseCase {

    suspend operator fun invoke(phoneNumber: String): Result<String>

    class Base(
        private val repo: SCRMRepository
    ) : StartVerificationChannelUseCase {

        override suspend fun invoke(phoneNumber: String): Result<String> = runCatching {
            val formattedPhoneNumber = phoneNumber.trim()
            repo.verificationChannelBegin(
                IncomeChannelData(
                    channelType = 0,
                    channelData = formattedPhoneNumber
                )
            ).throwOnFailure()
            formattedPhoneNumber
        }
    }
}