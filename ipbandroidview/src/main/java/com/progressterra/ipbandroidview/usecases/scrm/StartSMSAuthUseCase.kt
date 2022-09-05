package com.progressterra.ipbandroidview.usecases.scrm

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.VerificationType

interface StartSMSAuthUseCase {

    suspend fun startAuth(phoneNumber: String): Boolean

    class Base(
        private val sCRMRepository: SCRMRepository
    ) : StartSMSAuthUseCase {

        override suspend fun startAuth(phoneNumber: String): Boolean =
            sCRMRepository.startVerificationChannel(VerificationType.PHONE, phoneNumber).getOrNull()
                ?: false
    }
}