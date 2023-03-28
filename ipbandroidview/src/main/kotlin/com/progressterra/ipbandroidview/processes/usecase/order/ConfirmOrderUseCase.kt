package com.progressterra.ipbandroidview.processes.usecase.order

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.ext.throwOnFailure
import com.progressterra.ipbandroidview.composable.component.OrderProcessingComponentState

interface ConfirmOrderUseCase {

    suspend operator fun invoke(): Result<OrderProcessingComponentState>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val cartRepository: CartRepository
    ) : ConfirmOrderUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<OrderProcessingComponentState> = withToken { token ->
            try {
                cartRepository.confirmOrder(token).throwOnFailure()
                OrderProcessingComponentState(
                    success = true,
                    additionalInfo = "Заказ успешно создан!"
                )
            } catch (e: Exception) {
                OrderProcessingComponentState(
                    success = false,
                    additionalInfo = "Что-то пошло не так!"
                )
            }
        }
    }
}