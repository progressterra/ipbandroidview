package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.entities.toDatingUser
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.user.FetchAvatarUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface FetchDatingUserUseCase {

    suspend operator fun invoke(): Result<DatingUser>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val service: ImhService,
        private val fetchAvatarUseCase: FetchAvatarUseCase, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : FetchDatingUserUseCase, AbstractTokenUseCase(
        obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke(): Result<DatingUser> = withToken { token ->
            val avatar = fetchAvatarUseCase().getOrThrow()
            service.clientDataData(token).data?.toDatingUser(own = true)?.copy(image = avatar)!!
        }
    }
}