package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.ipbfavpromorec.IPBFavPromoRecRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.model.TypeEntities
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation

interface FetchFavoriteIds {

    suspend operator fun invoke(): Result<List<String>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val ipbFavPromoRecRepository: IPBFavPromoRecRepository
    ) : FetchFavoriteIds, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<List<String>> = withToken { token ->
            ipbFavPromoRecRepository.getClientEntityByType(
                token, TypeEntities.ONE.ordinal
            ).getOrThrow()!!
        }
    }
}