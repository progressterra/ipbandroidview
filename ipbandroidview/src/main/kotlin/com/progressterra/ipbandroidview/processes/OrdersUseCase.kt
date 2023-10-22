package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidview.features.ordercompact.OrderCompactState
import com.progressterra.ipbandroidview.shared.PagingUseCase

interface OrdersUseCase : PagingUseCase<Nothing, OrderCompactState> {

    class Base(
        private val obtainAccessToken: ObtainAccessToken,
        private val cartRepository: CartRepository
    ) : OrdersUseCase, PagingUseCase.Abstract<Nothing, OrderCompactState>() {

        override fun createSource() = OrdersSource(cartRepository, obtainAccessToken)
    }
}