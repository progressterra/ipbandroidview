package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.mapper.GoodsColorMapper
import com.progressterra.ipbandroidview.domain.mapper.GoodsDetailsMapper
import com.progressterra.ipbandroidview.model.store.GoodsDetails

interface GoodsDetailsUseCase {

    suspend operator fun invoke(id: String): Result<GoodsDetails>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val ieCommerceCoreRepository: IECommerceCoreRepository,
        private val cartRepository: CartRepository,
        private val goodsDetailsMapper: GoodsDetailsMapper,
        private val favoriteIdsUseCase: FavoriteIdsUseCase,
        private val productRepository: ProductRepository,
        private val colorMapper: GoodsColorMapper
    ) : AbstractUseCase(scrmRepository, provideLocation), GoodsDetailsUseCase {

        override suspend fun invoke(id: String): Result<GoodsDetails> = withToken { token ->
            val isFavorite = favoriteIdsUseCase().getOrThrow().contains(id)
            val count = cartRepository.getGoodsQuantity(token, id).getOrThrow()
            val goods = ieCommerceCoreRepository.getProductDetailByIDRG(id)
                .getOrThrow()!!.listProducts!!.first()
            val colors =
                productRepository.colorsForItem(id).getOrThrow()!!.map { colorMapper.map(it) }
            val sizeTableUrl = productRepository.sizeTableForItem(id).getOrThrow() ?: ""
            goodsDetailsMapper.map(
                goods,
                isFavorite,
                count?.count ?: 0,
                colors,
                emptyList(),
                sizeTableUrl
            )
        }
    }
}