package com.progressterra.ipbandroidview.processes.cart

import com.progressterra.ipbandroidapi.api.cart.CartService
import com.progressterra.ipbandroidapi.api.cart.models.IncomeDataAddProductFullPrice
import com.progressterra.ipbandroidapi.api.cart.models.StatusResult
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.entities.sum
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.entities.toSimplePrice
import com.progressterra.ipbandroidview.pages.cart.CartScreenState
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.ToastedException
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.widgets.cartitems.CartItemsState
import com.progressterra.ipbandroidview.widgets.cartsummary.CartSummaryState

interface RemoveFromCartUseCase {

    suspend operator fun invoke(goodsId: String, count: Int = 1): Result<CartScreenState>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val cartRepo: CartService,
        private val productRepository: ProductRepository, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : RemoveFromCartUseCase, AbstractTokenUseCase(
        obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke(goodsId: String, count: Int): Result<CartScreenState> =
            withToken { token ->
                val goods = cartRepo.deleteFromCart(
                    token,
                    IncomeDataAddProductFullPrice(goodsId, 1)
                ).also {
                    if (it.result?.status != StatusResult.SUCCESS) throw ToastedException(
                        it.result?.message ?: ""
                    )
                }.data?.listDRSale?.mapNotNull {
                    val oneGoods =
                        productRepository.productByNomenclatureId(token, it.idrfNomenclature!!)
                            .getOrThrow()?.toGoodsItem()?.toCartCardState()
                    oneGoods?.copy(
                        price = it.amountEndPrice?.toSimplePrice() ?: SimplePrice(),
                        counter = oneGoods.counter.copy(count = it.quantity ?: 0)
                    )
                } ?: emptyList()
                CartScreenState(
                    items = CartItemsState(goods),
                    summary = CartSummaryState(
                        total = goods.map { it.price }.sum()
                    )
                )
            }
    }
}