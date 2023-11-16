package com.progressterra.ipbandroidview.processes.order

import com.progressterra.ipbandroidapi.api.cart.CartService
import com.progressterra.ipbandroidview.features.ordercompact.OrderCompactState
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.PagingUseCase

interface OrdersUseCase : PagingUseCase<Nothing, OrderCompactState> {

    class Base(
        private val obtainAccessToken: ObtainAccessToken,
        private val cartRepository: CartService
    ) : OrdersUseCase, PagingUseCase.Abstract<Nothing, OrderCompactState>() {

        override fun createSource() = OrdersSource(cartRepository, obtainAccessToken)
    }
}