package com.progressterra.ipbandroidview.processes.cart

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.cart.models.IncomeDataAddProductFullPrice
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.entities.pricesSum
import com.progressterra.ipbandroidview.entities.toCartCardState
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.entities.toSimplePrice
import com.progressterra.ipbandroidview.pages.cart.CartState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.widgets.cartitems.CartItemsState
import com.progressterra.ipbandroidview.widgets.cartsummary.CartSummaryState

interface AddToCartUseCase {

    suspend operator fun invoke(goodsId: String, count: Int = 1): Result<CartState>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val cartRepo: CartRepository,
        private val productRepository: ProductRepository
    ) : AddToCartUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(goodsId: String, count: Int): Result<CartState> =
            withToken { token ->
                val goods = cartRepo.addToCart(
                    token,
                    IncomeDataAddProductFullPrice(
                        idrfNomenclature = goodsId,
                        count = 1
                    )
                ).getOrThrow()!!.listDRSale?.mapNotNull {
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
                        total = pricesSum(goods.map { it.price })
                    )
                )
            }
    }
}
