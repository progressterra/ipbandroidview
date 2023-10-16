package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidview.entities.Interest
import com.progressterra.ipbandroidview.entities.LocationPoint
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface DeleteReadyToMeetUseCase {

    suspend operator fun invoke(): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val imhService: ImhService
    ) : DeleteReadyToMeetUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<Unit> =
            withToken { token ->
                imhService.clientDataReadyMeetDelete(
                    token = token
                )
            }
    }
}