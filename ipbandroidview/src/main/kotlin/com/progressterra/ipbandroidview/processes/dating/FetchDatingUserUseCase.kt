package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.entities.toDatingUser
import com.progressterra.ipbandroidview.processes.BitmapImageUseCase
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractCacheTokenUseCase
import com.progressterra.ipbandroidview.shared.CacheUseCase
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.shared.log

interface FetchDatingUserUseCase : CacheUseCase<DatingUser> {

    suspend operator fun invoke()

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val service: ImhService,
        private val bitmapImageUseCase: BitmapImageUseCase,
        makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : FetchDatingUserUseCase, AbstractCacheTokenUseCase<DatingUser>(
        obtainAccessToken,
        makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke() {
            withCache { token ->
                val result = service.clientDataData(token).data?.toDatingUser(own = true)!!
                val avatarBitmap = bitmapImageUseCase(result.avatar).getOrThrow()
                result.copy(avatarBitmap = avatarBitmap).also {
                    log("CurrentUser", it.toString())
                }
            }
        }
    }
}