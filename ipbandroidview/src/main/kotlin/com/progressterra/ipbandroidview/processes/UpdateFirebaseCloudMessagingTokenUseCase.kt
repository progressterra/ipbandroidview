package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidapi.api.scrm.ScrmService
import com.progressterra.ipbandroidapi.api.scrm.models.OsType
import com.progressterra.ipbandroidapi.api.scrm.models.RGDeviceTokenEntity
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface UpdateFirebaseCloudMessagingTokenUseCase {

    suspend operator fun invoke(firebaseCloudMessagingToken: String): Result<Unit>

    class Base(
        private val repo: ScrmService,
        obtainAccessToken: ObtainAccessToken
    ) : UpdateFirebaseCloudMessagingTokenUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(
            firebaseCloudMessagingToken: String
        ): Result<Unit> = withToken { token ->
            repo.setDeviceToken(
                token = token,
                body = RGDeviceTokenEntity(
                    osType = OsType.ANDROID,
                    tokenData = firebaseCloudMessagingToken
                )
            )
        }
    }
}