package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.entities.Interest
import com.progressterra.ipbandroidview.entities.toDatingUser
import com.progressterra.ipbandroidview.processes.BitmapImageUseCase
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.occupacion.FetchUserOccupationUseCase
import com.progressterra.ipbandroidview.processes.user.FetchAvatarUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractCacheTokenUseCase
import com.progressterra.ipbandroidview.shared.CacheUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface FetchDatingUserUseCase : CacheUseCase<DatingUser> {

    suspend operator fun invoke()

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val service: ImhService,
        private val fetchAvatarUseCase: FetchAvatarUseCase, makeToastUseCase: MakeToastUseCase,
        private val bitmapImageUseCase: BitmapImageUseCase,
        private val fetchUserOccupationUseCase: FetchUserOccupationUseCase,
        manageResources: ManageResources
    ) : FetchDatingUserUseCase, AbstractCacheTokenUseCase<DatingUser>(
        obtainAccessToken,
        makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke() {
            withCache { token ->
                val avatar = fetchAvatarUseCase().getOrThrow()
                val avatarBitmap = bitmapImageUseCase(avatar).getOrThrow()
                val occupation = fetchUserOccupationUseCase().getOrThrow()
                service.clientDataData(token).data?.toDatingUser(own = true)
                    ?.copy(avatar = avatar, avatarBitmap = avatarBitmap, occupation = occupation?: Interest())!!
            }
        }
    }
}