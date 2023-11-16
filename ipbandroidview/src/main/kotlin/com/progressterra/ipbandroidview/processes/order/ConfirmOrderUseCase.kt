package com.progressterra.ipbandroidview.processes.order

import com.progressterra.ipbandroidapi.api.cart.CartService
import com.progressterra.ipbandroidapi.api.cart.models.StatusResult
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.features.ordernumber.OrderNumberState
import com.progressterra.ipbandroidview.pages.orderstatus.OrderStatusScreenState
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.ToastedException
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase

interface ConfirmOrderUseCase {

    suspend operator fun invoke(): Result<OrderStatusScreenState>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val cartRepository: CartService,
        private val productRepository: ProductRepository, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : ConfirmOrderUseCase, AbstractTokenUseCase(
        obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke(): Result<OrderStatusScreenState> = withToken { token ->
            val result = cartRepository.confirmOrder(token).also {
                if (it.result?.status != StatusResult.SUCCESS) throw ToastedException(
                    it.result?.message ?: ""
                )
            }.data
            val images = result?.listDRSale?.mapNotNull {
                productRepository.productByNomenclatureId(
                    token,
                    it.idrfNomenclature!!
                ).getOrThrow()?.toGoodsItem()?.image
            } ?: emptyList()
            cartRepository.paymentInternal(token).also {
                if (it.result?.status != StatusResult.SUCCESS) throw ToastedException(
                    it.result?.message ?: ""
                )
            }
            OrderStatusScreenState(
                id = result?.idUnique!!,
                number = OrderNumberState(
                    number = result.number ?: "",
                    success = true,
                    quantity = images.size,
                    address = result.adressString ?: ""
                )
            )
        }
    }
}