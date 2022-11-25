package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.purchases.PurchasesRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.mapper.ImageMapper
import com.progressterra.ipbandroidview.domain.mapper.StatusOrderMapper
import com.progressterra.ipbandroidview.model.OrderDetails
import com.progressterra.ipbandroidview.model.OrderGoods

interface OrdersUseCase {

    suspend fun orders(): Result<List<OrderDetails>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        manageResources: ManageResources,
        private val purchasesRepository: PurchasesRepository,
        private val cartRepository: CartRepository,
        private val imageMapper: ImageMapper,
        private val statusOrderMapper: StatusOrderMapper
    ) : OrdersUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun orders(): Result<List<OrderDetails>> = withToken { token ->
            purchasesRepository.getShopList(token).getOrThrow()?.map { purchase ->
                purchasesRepository.getPurchaseDetails(purchase.purchaseId!!).getOrThrow()
            }?.map { details ->
                OrderDetails(
                    id = details?.purchaseId!!,
                    number = details.number ?: noData,
                    goods = details.productsInfo?.map { product ->
                        OrderGoods(
                            id = product.productId ?: noData,
                            inCartCounter = details.productsInfo?.count { it.productId == product.productId }
                                ?: 0,
                            image = imageMapper.map(product.productImageDataJson ?: "")
                        )
                    } ?: emptyList()
                )
            }?.map {
                val result = cartRepository.getOrderById(it.id).getOrThrow()
                it.copy(status = result?.statusOrder?.let { status -> statusOrderMapper.map(status) }
                    ?: noData)
            } ?: emptyList()
        }
    }
}