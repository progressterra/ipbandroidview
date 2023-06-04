package com.progressterra.ipbandroidview.processes.store

import com.progressterra.ipbandroidapi.api.ipbfavpromorec.IPBFavPromoRecRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.model.TypeEntities
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.processes.location.ProvideLocation

interface FetchFavoriteIds {

    suspend operator fun invoke(): Result<List<String>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val ipbFavPromoRecRepository: IPBFavPromoRecRepository
    ) : FetchFavoriteIds, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<List<String>> = withToken { token ->
            ipbFavPromoRecRepository.getClientEntityByType(
                token, TypeEntities.ONE.ordinal
            ).getOrThrow()!!
        }
    }
}