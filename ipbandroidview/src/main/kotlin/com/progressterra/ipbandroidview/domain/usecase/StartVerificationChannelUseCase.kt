package com.progressterra.ipbandroidview.domain.usecase

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.IncomeChannelData

interface StartVerificationChannelUseCase {

    suspend fun start(phoneNumber: String): Result<Unit>

    class Base(
        private val repo: SCRMRepository
    ) : StartVerificationChannelUseCase {

        override suspend fun start(phoneNumber: String): Result<Unit> = runCatching {
            repo.verificationChannelBegin(
                IncomeChannelData(
                    channelType = 0,
                    channelData = phoneNumber
                )
            ).onFailure { throw it }
        }
    }
}