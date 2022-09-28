package com.progressterra.ipbandroidview.domain.endverification

interface EndVerificationChannelUseCase {

    suspend fun end(phoneNumber: String, code: String): Boolean
}