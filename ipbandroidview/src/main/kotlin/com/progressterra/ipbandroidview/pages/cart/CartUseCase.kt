package com.progressterra.ipbandroidview.pages.cart

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.entities.sum
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.entities.toSimplePrice
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.widgets.cartitems.CartItemsState
import com.progressterra.ipbandroidview.widgets.cartsummary.CartSummaryState


interface CartUseCase {

    suspend operator fun invoke(): Result<CartState>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val cartRepo: CartRepository,
        private val productRepository: ProductRepository
    ) : CartUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<CartState> = withToken { token ->
            val goods = cartRepo.cart(token).getOrThrow()!!.listDRSale?.mapNotNull {
                val oneGoods =
                    productRepository.productByNomenclatureId(token, it.idrfNomenclature!!)
                        .getOrThrow()?.toGoodsItem()?.toCartCardState()
                oneGoods?.copy(
                    price = it.amountEndPrice?.toSimplePrice() ?: SimplePrice(),
                    counter = oneGoods.counter.copy(count = it.quantity ?: 0)
                )
            } ?: emptyList()
            CartState(
                items = CartItemsState(goods),
                summary = CartSummaryState(
                    total = goods.map { it.price }.sum()
                )
            )
        }
    }
}
