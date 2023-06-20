package com.progressterra.ipbandroidview.pages.orders

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.cart.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.cart.models.SortData
import com.progressterra.ipbandroidapi.api.cart.models.TypeVariantSort
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.entities.toOrder
import com.progressterra.ipbandroidview.entities.toOrderDetailsOrderGoods
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetails
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface OrdersUseCase {

    suspend operator fun invoke(): Result<List<OrderDetails>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val manageResources: ManageResources,
        private val cartRepository: CartRepository,
        private val productRepository: ProductRepository
    ) : OrdersUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<List<OrderDetails>> = withToken { token ->
            cartRepository.orders(
                accessToken = token,
                income = FilterAndSort(
                    listFields = emptyList(),
                    sort = SortData(
                        fieldName = "dateUpdated",
                        variantSort = TypeVariantSort.ASC
                    ),
                    searchData = "",
                    skip = 0,
                    take = 300
                )
            ).getOrThrow()?.map {
                val order = it.toOrder(manageResources)
                val goods =
                    order.itemsIds.map { id ->
                        productRepository.productByNomenclatureId(token, id).getOrThrow()
                            ?.toGoodsItem()?.toOrderDetailsOrderGoods() ?: OrderDetails.OrderGoods()
                    }
                OrderDetails(
                    id = order.id,
                    number = order.number,
                    goods = goods,
                    status = order.status
                )
            } ?: emptyList()
        }
    }
}