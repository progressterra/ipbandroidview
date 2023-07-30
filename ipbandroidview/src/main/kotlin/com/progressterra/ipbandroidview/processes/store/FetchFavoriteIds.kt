package com.progressterra.ipbandroidview.processes.store

import com.progressterra.ipbandroidapi.api.ipbfavpromorec.IPBFavPromoRecRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.model.TypeEntities
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface FetchFavoriteIds {

    suspend operator fun invoke(): Result<List<String>>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val ipbFavPromoRecRepository: IPBFavPromoRecRepository
    ) : FetchFavoriteIds, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<List<String>> = withToken { token ->
            ipbFavPromoRecRepository.getClientEntityByType(
                token, TypeEntities.ONE.ordinal
            ).getOrThrow()!!
        }
    }
}