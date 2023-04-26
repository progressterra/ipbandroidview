package com.progressterra.ipbandroidview.processes.store

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.purchases.PurchasesRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.processes.mapper.PriceMapper
import com.progressterra.ipbandroidview.processes.mapper.StatusOrderMapper
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface OrdersUseCase {

    suspend operator fun invoke(): Result<List<OrderDetails>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        manageResources: ManageResources,
        private val purchasesRepository: PurchasesRepository,
        private val cartRepository: CartRepository,
        private val statusOrderMapper: StatusOrderMapper,
        private val priceMapper: PriceMapper
    ) : OrdersUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun invoke(): Result<List<OrderDetails>> = withToken { token ->
            purchasesRepository.getShopList(token).getOrThrow()?.map { purchase ->
                purchasesRepository.getPurchaseDetails(purchase.purchaseId!!).getOrThrow()
            }?.map { details ->
                OrderDetails(
                    id = details?.purchaseId!!,
                    number = details.number ?: noData,
                    goods = details.productsInfo?.map { product ->
                        val count =
                            details.productsInfo?.count { it.productId == product.productId } ?: 0
                        OrderGoods(
                            id = product.productId ?: noData,
                            inCartCounter = count,
                            image = "",
                            name = product.productName ?: noData,
                            totalPrice = priceMapper.map(count * (product.price ?: 0.0))
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