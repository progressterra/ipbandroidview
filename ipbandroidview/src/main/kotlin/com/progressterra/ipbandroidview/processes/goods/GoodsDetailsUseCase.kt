package com.progressterra.ipbandroidview.processes.goods

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.ProvideLocation
import com.progressterra.ipbandroidview.processes.mapper.GoodsDetailsMapper
import com.progressterra.ipbandroidview.entities.GoodsItem
import com.progressterra.ipbandroidview.processes.usecase.store.FetchFavoriteIds

interface GoodsDetailsUseCase {

    suspend operator fun invoke(id: String): Result<GoodsItem>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val ieCommerceCoreRepository: IECommerceCoreRepository,
        private val cartRepository: CartRepository,
        private val goodsDetailsMapper: GoodsDetailsMapper,
        private val fetchFavoriteIds: FetchFavoriteIds
    ) : AbstractUseCase(scrmRepository, provideLocation), GoodsDetailsUseCase {

        override suspend fun invoke(id: String): Result<GoodsItem> = withToken { token ->
            val isFavorite = fetchFavoriteIds().getOrThrow().contains(id)
            val count = cartRepository.getGoodsQuantity(token, id).getOrThrow()
            val goods = ieCommerceCoreRepository.getProductDetailByIDRG(id)
                .getOrThrow()!!.listProducts!!.first()
            goodsDetailsMapper.map(
                goods,
                isFavorite,
                count?.count ?: 0,
//                emptyList(),
            )
        }
    }
}