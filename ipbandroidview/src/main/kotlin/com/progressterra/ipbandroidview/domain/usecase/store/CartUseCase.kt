package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.mapper.CartGoodsMapper
import com.progressterra.ipbandroidview.domain.mapper.PriceMapper
import com.progressterra.ipbandroidview.model.Cart

interface CartUseCase {

    suspend fun cart(): Result<Cart>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val cartRepo: CartRepository,
        private val favoriteIdsUseCase: FavoriteIdsUseCase,
        private val iECommerceCoreRepository: IECommerceCoreRepository,
        private val cartGoodsMapper: CartGoodsMapper,
        private val priceMapper: PriceMapper
    ) : CartUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun cart(): Result<Cart> = runCatching {
            val cart = withToken { cartRepo.getProductsInCart(it) }.getOrThrow()
            val favoriteIds = if (cart?.drSaleRow.isNullOrEmpty()) emptyList()
            else favoriteIdsUseCase.favoriteIds().getOrThrow()
            val price = priceMapper.map(cart?.drSaleRow?.sumOf { it.endPrice ?: 0.0 } ?: 0.0)
            val goods = buildList {
                cart?.drSaleRow?.map { saleRow ->
                    saleRow.idrgGoodsInventory?.let { id ->
                        iECommerceCoreRepository.getProductDetailByIDRG(
                            id
                        ).getOrThrow()?.listProducts?.firstOrNull()?.let {
                            add(cartGoodsMapper.map(it, favoriteIds.contains(id)))
                        }
                    }
                }
            }
            Cart(
                listGoods = goods
            )
        }
    }
}