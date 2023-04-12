package com.progressterra.ipbandroidview.pages.cart

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.processes.mapper.PriceMapper
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState
import com.progressterra.ipbandroidview.widgets.cartitems.CartItemsState
import com.progressterra.ipbandroidview.widgets.cartsummary.CartSummaryState

interface CartUseCase {

    suspend operator fun invoke(): Result<CartState>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val cartRepo: CartRepository,
        private val iECommerceCoreRepository: IECommerceCoreRepository,
        private val cartGoodsMapper: CartGoodsMapper,
        private val priceMapper: PriceMapper
    ) : CartUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<CartState> = withToken { token ->
            val cart = cartRepo.getProductsInCart(token).getOrThrow()
            val price = priceMapper.map(cart?.drSaleRow?.sumOf { it.endPrice ?: 0.0 } ?: 0.0)
            val goods = buildSet {
                cart?.drSaleRow?.map { saleRow ->
                    saleRow.idrgGoodsInventory?.let { id ->
                        iECommerceCoreRepository.getProductDetailByIDRG(
                            id
                        ).getOrThrow()?.listProducts?.firstOrNull()?.let { goodsDetails ->
                            val count =
                                cart.drSaleRow?.count { it.idrfNomnclatura == goodsDetails.idrfNomenclatura }
                                    ?: 0
                            add(
                                cartGoodsMapper.map(goodsDetails)
                                    .copy(counter = CounterState(id, count))
                            )
                        }
                    }
                }
            }.toList()
            CartState(
                items = CartItemsState(goods),
                summary = CartSummaryState(
                    total = price
                )
            )
        }
    }
}