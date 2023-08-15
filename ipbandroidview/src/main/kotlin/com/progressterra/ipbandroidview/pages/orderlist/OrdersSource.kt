package com.progressterra.ipbandroidview.pages.orderlist

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.cart.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.cart.models.SortData
import com.progressterra.ipbandroidapi.api.cart.models.TypeVariantSort
import com.progressterra.ipbandroidview.entities.toOrder
import com.progressterra.ipbandroidview.features.ordercompact.OrderCompactState
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractSource

class OrdersSource(
    private val cartRepository: CartRepository,
    private val obtainAccessToken: ObtainAccessToken
) : AbstractSource<Nothing, OrderCompactState>() {

    override val pageSize = 10

    override suspend fun loadPage(skip: Int, take: Int): Result<List<OrderCompactState>> =
        runCatching {
            cartRepository.orders(
                accessToken = obtainAccessToken().getOrThrow(), income = FilterAndSort(
                    listFields = emptyList(), sort = SortData(
                        fieldName = "dateAdded", variantSort = TypeVariantSort.DESC
                    ), searchData = "", skip = skip, take = take
                )
            ).getOrThrow()?.map {
                it.toOrder().toOrderCompactState()
            } ?: emptyList()
        }
}