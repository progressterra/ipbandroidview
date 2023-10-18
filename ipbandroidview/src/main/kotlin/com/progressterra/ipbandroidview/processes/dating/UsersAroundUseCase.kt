package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.entities.toAnotherUser
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractCacheTokenUseCase
import com.progressterra.ipbandroidview.shared.CacheUseCase

interface UsersAroundUseCase : CacheUseCase<List<DatingUser>> {

    suspend operator fun invoke(tier: Int)

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val imhService: ImhService
    ) : UsersAroundUseCase, AbstractCacheTokenUseCase<List<DatingUser>>(obtainAccessToken) {

        override suspend fun invoke(tier: Int) {
            withCache { token ->
                val range = when (tier) {
                    0 -> 0 to 10
                    1 -> 10 to 25
                    else -> 25 to 50
                }
                imhService.clientDataAround(
                    token = token, minMeter = range.first, maxMeter = range.second
                ).dataList?.map { it.toAnotherUser() } ?: emptyList()
            }
        }
    }
}