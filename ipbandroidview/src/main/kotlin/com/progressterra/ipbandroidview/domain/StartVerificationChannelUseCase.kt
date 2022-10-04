package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.VerificationType

interface StartVerificationChannelUseCase {

    suspend fun start(phoneNumber: String): Boolean

    class Base(
        private val repo: SCRMRepository
    ) : StartVerificationChannelUseCase {

        override suspend fun start(phoneNumber: String): Boolean =
            repo.startVerificationChannel(VerificationType.PHONE, phoneNumber).isSuccess
    }
}