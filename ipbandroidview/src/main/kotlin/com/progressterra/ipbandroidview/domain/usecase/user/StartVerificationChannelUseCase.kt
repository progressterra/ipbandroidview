package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.IncomeChannelData
import com.progressterra.ipbandroidview.ext.throwOnFailure

interface StartVerificationChannelUseCase {

    suspend operator fun invoke(phoneNumber: String): Result<Unit>

    class Base(
        private val repo: SCRMRepository
    ) : StartVerificationChannelUseCase {

        override suspend fun invoke(phoneNumber: String): Result<Unit> = runCatching {
            var formattedPhoneNumber = phoneNumber.trim()
            if (formattedPhoneNumber.startsWith('8'))
                formattedPhoneNumber = formattedPhoneNumber.replaceFirst('8', '7')
            repo.verificationChannelBegin(
                IncomeChannelData(
                    channelType = 0,
                    channelData = formattedPhoneNumber
                )
            ).throwOnFailure()
        }
    }
}