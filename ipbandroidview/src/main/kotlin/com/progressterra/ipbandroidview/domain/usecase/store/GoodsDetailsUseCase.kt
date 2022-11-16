package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.mapper.GoodsDetailsMapper
import com.progressterra.ipbandroidview.model.GoodsDetails

interface GoodsDetailsUseCase {

    suspend fun goodsDetails(id: String): Result<GoodsDetails>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val ieCommerceCoreRepository: IECommerceCoreRepository,
        private val goodsDetailsMapper: GoodsDetailsMapper,
        private val favoriteIdsUseCase: FavoriteIdsUseCase
    ) : AbstractUseCase(scrmRepository, provideLocation), GoodsDetailsUseCase {

        override suspend fun goodsDetails(id: String): Result<GoodsDetails> = runCatching {
            val isFavorite = favoriteIdsUseCase.favoriteIds().getOrThrow().contains(id)
            val goods = ieCommerceCoreRepository.getProductDetailByIDRG(id)
                .getOrThrow()!!.listProducts!!.first()
            goodsDetailsMapper.map(goods, isFavorite)
        }
    }
}