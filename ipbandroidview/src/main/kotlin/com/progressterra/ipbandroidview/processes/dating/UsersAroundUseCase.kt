package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidview.entities.Connection
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.entities.toDatingUser
import com.progressterra.ipbandroidview.processes.BitmapImageUseCase
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractCacheTokenUseCase
import com.progressterra.ipbandroidview.shared.CacheUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface UsersAroundUseCase : CacheUseCase<List<DatingUser>> {

    suspend operator fun invoke()

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val imhService: ImhService, makeToastUseCase: MakeToastUseCase,
        private val bitmapImageUseCase: BitmapImageUseCase,
        private val userConnectionStatusUseCase: UserConnectionStatusUseCase,
        manageResources: ManageResources
    ) : UsersAroundUseCase, AbstractCacheTokenUseCase<List<DatingUser>>(
        obtainAccessToken,
        makeToastUseCase, manageResources
    ) {

        override suspend fun invoke() {
            withCache { token ->
                imhService.clientDataAround(
                    token = token, minMeter = 0, maxMeter = 300
                ).dataList?.map {
                    val user = it.toDatingUser()
                    val bitmapAvatar = bitmapImageUseCase(user.avatar).getOrThrow()
                    val connection = userConnectionStatusUseCase(user.id).getOrThrow()
                    user.copy(avatarBitmap = bitmapAvatar, connection = connection ?: Connection())
                }?.sortedBy { it.distance } ?: emptyList()
            }
        }
    }
}