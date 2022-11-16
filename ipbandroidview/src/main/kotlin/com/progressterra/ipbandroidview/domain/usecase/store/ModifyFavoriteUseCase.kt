package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.ipbfavpromorec.IPBFavPromoRecRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.model.TypeOfEntity
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation

interface ModifyFavoriteUseCase {

    suspend fun modifyFavorite(id: String, favorite: Boolean): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val favoriteRepository: IPBFavPromoRecRepository
    ) : AbstractUseCase(scrmRepository, provideLocation), ModifyFavoriteUseCase {

        override suspend fun modifyFavorite(id: String, favorite: Boolean): Result<Unit> =
            runCatching {
                if (favorite)
                    withToken {
                        favoriteRepository.removeFromFavorite(
                            it,
                            id
                        )
                    }.onFailure { throw it }
                else
                    withToken {
                        favoriteRepository.addToFavorite(
                            it,
                            id,
                            TypeOfEntity.PRODUCT
                        )
                    }.onFailure { throw it }
            }
    }
}