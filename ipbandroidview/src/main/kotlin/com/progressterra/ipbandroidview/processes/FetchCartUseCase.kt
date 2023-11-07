package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidapi.api.cart.CartService
import com.progressterra.ipbandroidapi.api.cart.models.StatusResult
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.entities.sum
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.entities.toSimplePrice
import com.progressterra.ipbandroidview.pages.cart.CartScreenState
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractCacheTokenUseCase
import com.progressterra.ipbandroidview.shared.CacheUseCase
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.widgets.cartitems.CartItemsState
import com.progressterra.ipbandroidview.widgets.cartsummary.CartSummaryState


interface FetchCartUseCase : CacheUseCase<CartScreenState> {

    suspend operator fun invoke()

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val cartRepo: CartService,
        private val productRepository: ProductRepository, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : FetchCartUseCase, AbstractCacheTokenUseCase<CartScreenState>(obtainAccessToken,
        makeToastUseCase, manageResources
    ) {

        override suspend fun invoke() {
            withCache { token ->
                val goods = cartRepo.cart(token).also {
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
                UserData.cartCounter = goods.size
                CartScreenState(
                    items = CartItemsState(goods),
                    summary = CartSummaryState(
                        total = goods.map { it.price }.sum()
                    )
                )
            }
        }
    }
}
