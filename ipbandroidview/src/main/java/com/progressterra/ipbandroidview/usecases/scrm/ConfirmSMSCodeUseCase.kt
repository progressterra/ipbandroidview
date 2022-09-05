package com.progressterra.ipbandroidview.usecases.scrm

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.VerificationType

interface ConfirmSMSCodeUseCase {

    //TODO what is infoDevice?
    suspend fun confirmCode(phoneNumber: String, confirmCode: String): Boolean

    class Base(
        private val sCRMRepository: SCRMRepository
    ) : ConfirmSMSCodeUseCase {

        override suspend fun confirmCode(phoneNumber: String, confirmCode: String): Boolean =
            sCRMRepository.finishVerificationChannel(
                VerificationType.PHONE,
                phoneNumber,
                confirmCode,
                ""
            ).isSuccess
    }
}