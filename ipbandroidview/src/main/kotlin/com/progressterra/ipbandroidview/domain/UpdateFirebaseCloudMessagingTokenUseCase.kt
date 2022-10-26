package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.IncomeDeviceParameters
import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidview.data.ProvideLocation

interface UpdateFirebaseCloudMessagingTokenUseCase {

    suspend fun update(firebaseCloudMessagingToken: String): Result<Unit>

    class Base(
        private val repo: SCRMRepository,
        provideLocation: ProvideLocation
    ) : UpdateFirebaseCloudMessagingTokenUseCase, AbstractUseCaseWithToken(repo, provideLocation) {

        override suspend fun update(firebaseCloudMessagingToken: String): Result<Unit> = withToken {
            repo.setDeviceToken(
                accessToken = it, request = IncomeDeviceParameters(
                    idDivice = UserData.deviceId,
                    deviceToken = firebaseCloudMessagingToken
                )
            )
        }
    }
}