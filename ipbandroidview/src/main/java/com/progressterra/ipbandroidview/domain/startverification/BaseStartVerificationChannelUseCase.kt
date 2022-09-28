package com.progressterra.ipbandroidview.domain.startverification

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.VerificationType

class BaseStartVerificationChannelUseCase(
    private val repo: SCRMRepository
) : StartVerificationChannelUseCase {

    override suspend fun start(phoneNumber: String): Boolean =
        repo.startVerificationChannel(VerificationType.PHONE, phoneNumber).isSuccess
}