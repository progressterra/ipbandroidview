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

    suspend operator fun invoke(): Result<Cart>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val cartRepo: CartRepository,
        private val fetchFavoriteIds: FetchFavoriteIds,
        private val iECommerceCoreRepository: IECommerceCoreRepository,
        private val cartGoodsMapper: CartGoodsMapper,
        private val priceMapper: PriceMapper
    ) : CartUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<Cart> = withToken { token ->
            val cart = cartRepo.getProductsInCart(token).getOrThrow()
            val favoriteIds = if (cart?.drSaleRow.isNullOrEmpty()) emptyList()
            else fetchFavoriteIds().getOrThrow()
            val price = priceMapper.map(cart?.drSaleRow?.sumOf { it.endPrice ?: 0.0 } ?: 0.0)
            val goods = buildSet {
                cart?.drSaleRow?.map { saleRow ->
                    saleRow.idrgGoodsInventory?.let { id ->
                        iECommerceCoreRepository.getProductDetailByIDRG(
                            id
                        ).getOrThrow()?.listProducts?.firstOrNull()?.let { goodsDetails ->
                            add(
                                cartGoodsMapper.map(
                                    goodsDetails,
                                    favoriteIds.contains(id),
                                    cart.drSaleRow?.count { it.idrfNomnclatura == goodsDetails.idrfNomenclatura }
                                        ?: 0)
                            )
                        }
                    }
                }
            }.toList()
            Cart(
                listGoods = goods,
                totalPrice = price
            )
        }
    }
}