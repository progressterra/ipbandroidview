package com.progressterra.ipbandroidview.pages.orders

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.cart.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.cart.models.SortData
import com.progressterra.ipbandroidapi.api.cart.models.TypeVariantSort
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.entities.toOrder
import com.progressterra.ipbandroidview.entities.toOrderCardState
import com.progressterra.ipbandroidview.features.ordercard.OrderCardState
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.widgets.orderitems.OrderItemsState

interface OrdersUseCase {

    suspend operator fun invoke(): Result<List<OrderDetailsState>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val manageResources: ManageResources,
        private val cartRepository: CartRepository,
        private val productRepository: ProductRepository
    ) : OrdersUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<List<OrderDetailsState>> = withToken { token ->
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
                            ?.toGoodsItem()?.toOrderCardState() ?: OrderCardState()
                    }
                OrderDetailsState(
                    id = order.id,
                    number = order.number,
                    goods = OrderItemsState(goods),
                    status = order.status
                )
            } ?: emptyList()
        }
    }
}