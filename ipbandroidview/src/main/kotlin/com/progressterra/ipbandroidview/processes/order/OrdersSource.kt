package com.progressterra.ipbandroidview.processes.order

import com.progressterra.ipbandroidapi.api.cart.CartService
import com.progressterra.ipbandroidapi.api.cart.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.cart.models.SortData
import com.progressterra.ipbandroidapi.api.cart.models.StatusResult
import com.progressterra.ipbandroidapi.api.cart.models.TypeVariantSort
import com.progressterra.ipbandroidview.entities.toOrder
import com.progressterra.ipbandroidview.features.ordercompact.OrderCompactState
import com.progressterra.ipbandroidview.processes.ToastedException
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractSource

class OrdersSource(
    private val cartRepository: CartService,
    private val obtainAccessToken: ObtainAccessToken
) : AbstractSource<Nothing, OrderCompactState>() {

    override val pageSize = 10

    override suspend fun loadPage(
        skip: Int,
        take: Int
    ): Result<Pair<Int, List<OrderCompactState>>> =
        runCatching {
            val response = cartRepository.orders(
                accessToken = obtainAccessToken().getOrThrow(), income = FilterAndSort(
                    listFields = emptyList(), sort = SortData(
                        fieldName = "dateAdded", variantSort = TypeVariantSort.DESC
                    ), searchData = "", skip = skip, take = take
                )
            ).also {
                if (it.result?.status != StatusResult.SUCCESS) throw ToastedException(
                    it.result?.message ?: ""
                )
            }.dataList ?: emptyList()
            response.size to response.map { it.toOrder().toOrderCompactState() }
        }
}