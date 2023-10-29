package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidapi.api.scrm.ScrmService
import com.progressterra.ipbandroidapi.api.scrm.models.OsType
import com.progressterra.ipbandroidapi.api.scrm.models.RGDeviceTokenEntity
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface UpdateFirebaseCloudMessagingTokenUseCase {

    suspend operator fun invoke(firebaseCloudMessagingToken: String): Result<Unit>

    class Base(
        private val repo: ScrmService,
        obtainAccessToken: ObtainAccessToken, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : UpdateFirebaseCloudMessagingTokenUseCase, AbstractTokenUseCase(obtainAccessToken,
        makeToastUseCase, manageResources
    ) {

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