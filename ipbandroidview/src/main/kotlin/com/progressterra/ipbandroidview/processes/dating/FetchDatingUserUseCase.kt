package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.entities.toDatingUser
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.user.FetchAvatarUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface FetchDatingUserUseCase {

    suspend operator fun invoke(): Result<DatingUser>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val service: ImhService,
        private val fetchAvatarUseCase: FetchAvatarUseCase
    ) : FetchDatingUserUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<DatingUser> = withToken { token ->
            val avatar = fetchAvatarUseCase().getOrThrow()
            service.clientDataData(token).data?.toDatingUser()?.copy(image = avatar)!!
        }
    }
}