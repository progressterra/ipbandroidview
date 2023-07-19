package com.progressterra.ipbandroidview.pages.orderlist

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.cart.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.cart.models.SortData
import com.progressterra.ipbandroidapi.api.cart.models.TypeVariantSort
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.toOrder
import com.progressterra.ipbandroidview.entities.toOrderCompactState
import com.progressterra.ipbandroidview.features.ordercompact.OrderCompactState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface OrdersUseCase {

    suspend operator fun invoke(): Result<List<OrderCompactState>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val cartRepository: CartRepository
    ) : OrdersUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<List<OrderCompactState>> = withToken { token ->
            cartRepository.orders(
                accessToken = token, income = FilterAndSort(
                    listFields = emptyList(), sort = SortData(
                        fieldName = "dateUpdated", variantSort = TypeVariantSort.DESC
                    ), searchData = "", skip = 0, take = 300
                )
            ).getOrThrow()?.map {
                it.toOrder().toOrderCompactState()
            } ?: emptyList()
        }
    }
}