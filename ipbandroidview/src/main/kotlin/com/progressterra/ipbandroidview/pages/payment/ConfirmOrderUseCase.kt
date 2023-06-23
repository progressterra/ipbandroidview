package com.progressterra.ipbandroidview.pages.payment

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.features.orderid.OrderIdState
import com.progressterra.ipbandroidview.features.orderoverview.OrderOverviewState
import com.progressterra.ipbandroidview.pages.orderstatus.OrderStatusState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface ConfirmOrderUseCase {

    suspend operator fun invoke(): Result<OrderStatusState>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val cartRepository: CartRepository,
        private val productRepository: ProductRepository
    ) : ConfirmOrderUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<OrderStatusState> = withToken { token ->
            val result = cartRepository.confirmOrder(token).getOrThrow()
            val images = result?.listDRSale?.mapNotNull {
                productRepository.productByNomenclatureId(
                    token,
                    it.idrfNomenclature!!
                ).getOrThrow()?.toGoodsItem()?.imageUrl
            } ?: emptyList()
            val payment = cartRepository.paymentInternal(token).isSuccess
            OrderStatusState(
                orderId = OrderIdState(
                    id = result?.idUnique!!,
                    success = payment
                ), orderOverview = OrderOverviewState(
                    quantity = images.size,
                    goodsImages = images,
                    address = result.adressString ?: ""
                )
            )
        }
    }
}