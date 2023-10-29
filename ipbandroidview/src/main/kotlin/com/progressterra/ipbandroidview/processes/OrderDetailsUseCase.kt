package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.entities.toOrder
import com.progressterra.ipbandroidview.entities.toSimplePrice
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsState
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface OrderDetailsUseCase {

    suspend operator fun invoke(orderId: String): Result<OrderDetailsState>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val cartRepository: CartRepository,
        private val productRepository: ProductRepository, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : OrderDetailsUseCase, AbstractTokenUseCase(obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke(orderId: String): Result<OrderDetailsState> = withToken {
            val result =
                cartRepository.orderById(accessToken = it, idOrder = orderId).getOrThrow()!!
            val order = result.toOrder()
            val goods = result.listDRSale?.mapNotNull { dr ->
                productRepository.productByNomenclatureId(it, dr.idrfNomenclature!!)
                    .getOrThrow()
                    ?.toGoodsItem()
                    ?.toOrderCardState()?.copy(
                        oldPrice = (dr.amountBeginPrice?.toSimplePrice()
                            ?: SimplePrice()) * (dr.quantity ?: 0),
                        count = dr.quantity ?: 0,
                        price = (dr.amountEndPrice?.toSimplePrice()
                            ?: SimplePrice()) * (dr.quantity ?: 0)
                    )
            } ?: emptyList()
            order.toOrderDetailsState(goods)
        }
    }
}