package com.progressterra.ipbandroidview.domain.startverification

interface StartVerificationChannelUseCase {

    suspend fun start(phoneNumber: String): Boolean

}