package com.progressterra.ipbandroidview.pages.orders

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.purchases.PurchasesRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetails
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
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
        private val gson: Gson
    ) : OrdersUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

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
                        OrderDetails.OrderGoods(
                            id = product.productId ?: noData,
                            inCartCounter = count,
                            image = parseImage(product.productImageDataJson ?: "")
                        )
                    } ?: emptyList()
                )
            }?.map {
                val result = cartRepository.getOrderById(it.id).getOrThrow()
                it.copy(status = result?.statusOrder?.let { status -> statusOrderMapper.map(status) }
                    ?: noData)
            } ?: emptyList()
        }

        private fun parseImage(data: String): String = gson.fromJson(
            data, ImageData::class.java
        ).list.first().url

        data class ImageData(
            @SerializedName("datalist") val list: List<Item>
        ) {

            data class Item(
                @SerializedName("URL") val url: String
            )
        }
    }
}