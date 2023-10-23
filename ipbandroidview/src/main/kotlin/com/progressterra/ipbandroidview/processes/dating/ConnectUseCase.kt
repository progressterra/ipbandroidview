package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.IncomeDataIDClient
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface ConnectUseCase {

    suspend operator fun invoke(user: DatingUser): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val imhService: ImhService
    ) : ConnectUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(user: DatingUser): Result<Unit> = withToken {
            imhService.connect(
                token = it,
                body = IncomeDataIDClient(user.id)
            )
        }
    }
}