package com.progressterra.ipbandroidview.domain.updatefcm

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidview.base.ProvideLocation
import com.progressterra.ipbandroidview.domain.AbstractUseCaseWithToken

class BaseUpdateFirebaseCloudMessagingTokenUseCase(
    private val repo: SCRMRepository,
    provideLocation: ProvideLocation
) : UpdateFirebaseCloudMessagingTokenUseCase, AbstractUseCaseWithToken(repo, provideLocation) {

    override suspend fun update(firebaseCloudMessagingToken: String): Result<Unit> = withToken {
        repo.setDeviceToken(it, UserData.deviceId, firebaseCloudMessagingToken)
    }
}