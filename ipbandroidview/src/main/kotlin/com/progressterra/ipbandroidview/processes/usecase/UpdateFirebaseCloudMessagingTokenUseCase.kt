package com.progressterra.ipbandroidview.processes.usecase

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.IncomeDeviceParameters
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.ProvideLocation

interface UpdateFirebaseCloudMessagingTokenUseCase {

    suspend operator fun invoke(firebaseCloudMessagingToken: String): Result<Unit>

    class Base(
        private val repo: SCRMRepository,
        provideLocation: ProvideLocation
    ) : UpdateFirebaseCloudMessagingTokenUseCase, AbstractUseCase(repo, provideLocation) {

        override suspend fun invoke(
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