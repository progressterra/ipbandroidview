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
            repo.verificationChannelBegin(
                IncomeChannelData(
                    channelType = 0,
                    channelData = "7${phoneNumber.trim()}"
                )
            ).throwOnFailure()
        }
    }
}