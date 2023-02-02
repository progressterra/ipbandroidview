package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.ipbfavpromorec.IPBFavPromoRecRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.model.TypeEntities
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.ext.throwOnFailure

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
                ).throwOnFailure()
            else
                favoriteRepository.addToFavorite(
                    token,
                    id,
                    TypeEntities.ONE.ordinal
                ).throwOnFailure()
        }
    }
}