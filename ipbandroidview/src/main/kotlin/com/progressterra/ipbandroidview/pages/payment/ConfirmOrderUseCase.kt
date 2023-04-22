package com.progressterra.ipbandroidview.pages.payment

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.features.orderid.OrderIdState
import com.progressterra.ipbandroidview.features.orderoverview.OrderOverviewState
import com.progressterra.ipbandroidview.pages.orderstatus.OrderStatusState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractUseCase

interface ConfirmOrderUseCase {

    suspend operator fun invoke(): Result<OrderStatusState>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val cartRepository: CartRepository
    ) : ConfirmOrderUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        //TODO
        override suspend fun invoke(): Result<OrderStatusState> = withToken { token ->
            val result = cartRepository.confirmOrder(token).getOrThrow()
            OrderStatusState(
                orderId = OrderIdState(
                    id = result?.idUnique!!
                ), orderOverview = OrderOverviewState(
                    quantity = result.drSaleRow?.size ?: 0,
                )
            )
        }
    }

    class Test : ConfirmOrderUseCase {

        override suspend fun invoke(): Result<OrderStatusState> = Result.success(
            OrderStatusState(
                orderId = OrderIdState(id = "0-000-001"), orderOverview = OrderOverviewState(
                    quantity = 5,
                    estimatedDelivery = "13.07.2023",
                    address = "ул. Пушкина, д. 13",
                    images = listOf(
                        "https://images.unsplash.com/photo-1611915387288-fd8d2f5f928b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8&w=1000&q=80",
                        "https://images.unsplash.com/photo-1611915387288-fd8d2f5f928b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8&w=1000&q=80",
                        "https://images.unsplash.com/photo-1611915387288-fd8d2f5f928b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8&w=1000&q=80",
                        "https://images.unsplash.com/photo-1611915387288-fd8d2f5f928b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8&w=1000&q=80",
                        "https://images.unsplash.com/photo-1611915387288-fd8d2f5f928b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8&w=1000&q=80"
                    )
                )
            )
        )
    }
}