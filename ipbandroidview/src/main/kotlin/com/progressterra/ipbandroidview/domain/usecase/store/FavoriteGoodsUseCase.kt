package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.IPBFavPromoRecRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.model.TypeOfEntity
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.mapper.StoreGoodsMapper
import com.progressterra.ipbandroidview.model.StoreGoods

interface FavoriteGoodsUseCase {

    suspend fun favoriteGoods(): Result<List<StoreGoods>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val favoriteRepository: IPBFavPromoRecRepository,
        private val eIECommerceCoreRepository: IECommerceCoreRepository,
        private val storeGoodsMapper: StoreGoodsMapper
    ) : AbstractUseCase(scrmRepository, provideLocation), FavoriteGoodsUseCase {

        override suspend fun favoriteGoods(): Result<List<StoreGoods>> = withToken { token ->
            val favoriteIds = favoriteRepository.getClientEntityByType(
                token, TypeOfEntity.PRODUCT
            ).getOrThrow()
            buildList {
                favoriteIds.map { favoriteId ->
                    eIECommerceCoreRepository.getProductDetailByIDRG(
                        favoriteId
                    ).getOrThrow()?.listProducts?.firstOrNull()?.let {
                        add(storeGoodsMapper.map(it, true))
                    }
                }
            }
        }
    }
}