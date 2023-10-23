package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.IncomeDataIDConnect
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface AcceptConnectUseCase {

    suspend operator fun invoke(user: DatingUser): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val imhService: ImhService
    ) : AcceptConnectUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(user: DatingUser): Result<Unit> = withToken {
            imhService.connectApprove(
                token = it,
                body = IncomeDataIDConnect(idConnect = user.connectionId)
            )
        }
    }
}