package com.progressterra.ipbandroidview.pages.orderlist

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.cart.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.cart.models.SortData
import com.progressterra.ipbandroidapi.api.cart.models.TypeVariantSort
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.entities.toOrder
import com.progressterra.ipbandroidview.entities.toOrderCardState
import com.progressterra.ipbandroidview.entities.toOrderCompactState
import com.progressterra.ipbandroidview.entities.toSimplePrice
import com.progressterra.ipbandroidview.features.ordercompact.OrderCompactState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface OrdersUseCase {

    suspend operator fun invoke(): Result<List<OrderCompactState>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val cartRepository: CartRepository,
        private val productRepository: ProductRepository
    ) : OrdersUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<List<OrderCompactState>> = withToken { token ->
            cartRepository.orders(
                accessToken = token, income = FilterAndSort(
                    listFields = emptyList(), sort = SortData(
                        fieldName = "dateUpdated", variantSort = TypeVariantSort.DESC
                    ), searchData = "", skip = 0, take = 300
                )
            ).getOrThrow()?.map {
                val order = it.toOrder()
                val goods = it.listDRSale?.mapNotNull { dr ->
                    productRepository.productByNomenclatureId(token, dr.idrfNomenclature!!)
                        .getOrThrow()
                        ?.toGoodsItem()
                        ?.toOrderCardState()?.copy(
                            count = dr.quantity ?: 0,
                            price = (dr.amountEndPrice?.toSimplePrice()
                                ?: SimplePrice()) * (dr.quantity ?: 0)
                        )
                } ?: emptyList()
                order.toOrderCompactState(goods)
            } ?: emptyList()
        }
    }
}