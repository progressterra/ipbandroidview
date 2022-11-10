package com.progressterra.ipbandroidview.domain.usecase

import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.IPBFavPromoRecRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.model.TypeOfEntity
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.mapper.GoodsMapper
import com.progressterra.ipbandroidview.model.Goods

interface FavoriteGoodsUseCase {

    suspend fun favoriteGoods(): Result<List<Goods>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val favoriteRepository: IPBFavPromoRecRepository,
        private val eIECommerceCoreRepository: IECommerceCoreRepository,
        private val goodsMapper: GoodsMapper
    ) : AbstractUseCase(scrmRepository, provideLocation), FavoriteGoodsUseCase {

        override suspend fun favoriteGoods(): Result<List<Goods>> = runCatching {
            val favoriteIds = withToken { token ->
                favoriteRepository.getClientEntityByType(
                    token, TypeOfEntity.PRODUCT
                )
            }.getOrThrow()
            buildList {
                favoriteIds.map { favoriteId ->
                    eIECommerceCoreRepository.getProductDetailByIDRG(
                        favoriteId
                    ).getOrThrow()?.listProducts?.firstOrNull()?.let {
                        add(goodsMapper.map(it, true))
                    }
                }
            }
        }
    }
}