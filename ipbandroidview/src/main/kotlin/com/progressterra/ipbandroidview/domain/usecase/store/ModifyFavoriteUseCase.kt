package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.ipbfavpromorec.IPBFavPromoRecRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.model.TypeOfEntity
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation

interface ModifyFavoriteUseCase {

    suspend operator fun invoke(id: String, favorite: Boolean): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val favoriteRepository: IPBFavPromoRecRepository
    ) : AbstractUseCase(scrmRepository, provideLocation), ModifyFavoriteUseCase {

        override suspend fun invoke(
            id: String, favorite: Boolean
        ): Result<Unit> = withToken { token ->
            if (favorite)
                favoriteRepository.removeFromFavorite(
                    token,
                    id
                ).onFailure { throw it }
            else
                favoriteRepository.addToFavorite(
                    token,
                    id,
                    TypeOfEntity.PRODUCT
                ).onFailure { throw it }
        }
    }
}