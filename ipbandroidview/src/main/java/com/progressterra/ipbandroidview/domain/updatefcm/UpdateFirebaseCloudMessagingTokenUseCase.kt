package com.progressterra.ipbandroidview.domain.updatefcm

interface UpdateFirebaseCloudMessagingTokenUseCase {

    suspend fun update(firebaseCloudMessagingToken: String): Result<Unit>
}