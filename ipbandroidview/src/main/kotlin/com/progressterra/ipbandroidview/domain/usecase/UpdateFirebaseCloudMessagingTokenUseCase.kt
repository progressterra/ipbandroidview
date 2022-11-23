package com.progressterra.ipbandroidview.domain.usecase

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.IncomeDeviceParameters
import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation

interface UpdateFirebaseCloudMessagingTokenUseCase {

    suspend fun update(firebaseCloudMessagingToken: String): Result<Unit>

    class Base(
        private val repo: SCRMRepository,
        provideLocation: ProvideLocation
    ) : UpdateFirebaseCloudMessagingTokenUseCase, AbstractUseCase(repo, provideLocation) {

        override suspend fun update(
            firebaseCloudMessagingToken: String
        ): Result<Unit> = withToken { token ->
            repo.setDeviceToken(
                accessToken = token, request = IncomeDeviceParameters(
                    idDivice = UserData.deviceId,
                    deviceToken = firebaseCloudMessagingToken
                )
            )
        }
    }
}