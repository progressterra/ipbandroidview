package com.progressterra.ipbandroidview.pages.cart

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.entities.pricesSum
import com.progressterra.ipbandroidview.entities.toCartCardState
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.features.cartcard.CartCardState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState
import com.progressterra.ipbandroidview.widgets.cartitems.CartItemsState
import com.progressterra.ipbandroidview.widgets.cartsummary.CartSummaryState


interface CartUseCase {

    suspend operator fun invoke(): Result<CartState>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val cartRepo: CartRepository,
        private val productRepository: ProductRepository
    ) : CartUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<CartState> = withToken { token ->
            val goods = cartRepo.cart(token).getOrThrow()!!.listDRSale?.mapNotNull {
                productRepository.productByGoodsInventoryId(token, it.idrfNomenclature!!)
                    .getOrThrow()?.toGoodsItem()?.toCartCardState()
            } ?: emptyList()
            CartState(
                items = CartItemsState(goods),
                summary = CartSummaryState(
                    total = pricesSum(goods.map { it.price })
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