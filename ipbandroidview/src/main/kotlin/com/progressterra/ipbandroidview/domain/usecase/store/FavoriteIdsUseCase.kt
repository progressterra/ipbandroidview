package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.ipbfavpromorec.IPBFavPromoRecRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.model.TypeOfEntity
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation

interface FavoriteIdsUseCase {

    suspend fun favoriteIds(): Result<List<String>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val ipbFavPromoRecRepository: IPBFavPromoRecRepository
    ) : FavoriteIdsUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun favoriteIds(): Result<List<String>> = runCatching {
            withToken {
                ipbFavPromoRecRepository.getClientEntityByType(
                    it, TypeOfEntity.PRODUCT
                )
            }.getOrThrow()
        }
    }
}