package com.progressterra.ipbandroidview.pages.support

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.IncomeDeviceParameters
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData

interface UpdateFirebaseCloudMessagingTokenUseCase {

    suspend operator fun invoke(firebaseCloudMessagingToken: String): Result<Unit>

    class Base(
        private val repo: SCRMRepository,
        provideLocation: ProvideLocation
    ) : UpdateFirebaseCloudMessagingTokenUseCase, AbstractTokenUseCase(repo, provideLocation) {

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