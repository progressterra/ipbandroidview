package com.progressterra.ipbandroidview.pages.payment

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.features.ordernumber.OrderNumberState
import com.progressterra.ipbandroidview.pages.orderstatus.OrderStatusScreenState
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface ConfirmOrderUseCase {

    suspend operator fun invoke(): Result<OrderStatusScreenState>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val cartRepository: CartRepository,
        private val productRepository: ProductRepository
    ) : ConfirmOrderUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<OrderStatusScreenState> = withToken { token ->
            val result = cartRepository.confirmOrder(token).getOrThrow()
            val images = result?.listDRSale?.mapNotNull {
                productRepository.productByNomenclatureId(
                    token,
                    it.idrfNomenclature!!
                ).getOrThrow()?.toGoodsItem()?.image
            } ?: emptyList()
            val payment = cartRepository.paymentInternal(token).isSuccess
            OrderStatusScreenState(
                id = result?.idUnique!!,
                number = OrderNumberState(
                    number = result.number ?: "",
                    success = payment,
                    quantity = images.size,
                    address = result.adressString ?: ""
                )
            )
        }
    }
}