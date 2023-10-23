package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.entities.toDatingUser
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface FetchDatingUserUseCase {

    suspend operator fun invoke(): Result<DatingUser>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val service: ImhService
    ) : FetchDatingUserUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<DatingUser> = withToken { token ->
            service.clientDataData(token).data?.toDatingUser()!!
        }
    }
}