package com.progressterra.ipbandroidview.pages.goodsdetails

import com.progressterra.ipbandroidapi.api.ipbfavpromorec.IPBFavPromoRecRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.model.TypeEntities
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.throwOnFailure

interface ModifyFavoriteUseCase {

    suspend operator fun invoke(id: String, favorite: Boolean): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val favoriteRepository: IPBFavPromoRecRepository
    ) : AbstractTokenUseCase(obtainAccessToken), ModifyFavoriteUseCase {

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