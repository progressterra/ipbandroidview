package com.progressterra.ipbandroidview.domain.usecase.order

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.model.OrderResult

interface ConfirmOrderUseCase {

    suspend fun confirm(): Result<OrderResult>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val cartRepository: CartRepository
    ) : ConfirmOrderUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        //TODO replace
        override suspend fun confirm(): Result<OrderResult> = withToken { token ->
            try {
                cartRepository.confirmOrder(token).onFailure { throw it }
                OrderResult(success = true, additionalInfo = "Заказ успешно создан!")
            } catch (e: Exception) {
                OrderResult(success = false, additionalInfo = "Что-то пошло не так!")
            }
        }
    }
}