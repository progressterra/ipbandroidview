package com.progressterra.ipbandroidview.pages.cart

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.cartcard.CartCardState
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

    class Test : CartUseCase {

        override suspend fun invoke(): Result<CartState> = runCatching {
            CartState(
                items = CartItemsState(
                    listOf(
                        CartCardState(
                            id = "1",
                            name = "Товар 1",
                            imageUrl = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg",
                            price = SimplePrice(1000),
                            counter = CounterState("1", 1)
                        ),
                        CartCardState(
                            id = "2",
                            name = "Товар 2",
                            imageUrl = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg",
                            price = SimplePrice(2000),
                            counter = CounterState("2", 1)
                        ),
                        CartCardState(
                            id = "3",
                            name = "Товар 3",
                            imageUrl = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg",
                            price = SimplePrice(4444),
                            counter = CounterState("3", 1)
                        )
                    )
                ),
                summary = CartSummaryState(
                    total = SimplePrice(7444)
                )
            )
        }
    }
}