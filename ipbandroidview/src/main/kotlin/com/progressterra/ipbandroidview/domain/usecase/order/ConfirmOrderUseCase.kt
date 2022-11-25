package com.progressterra.ipbandroidview.domain.usecase.order

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation

interface ConfirmOrderUseCase {

    suspend fun confirm(): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val cartRepository: CartRepository
    ) : ConfirmOrderUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun confirm(): Result<Unit> = withToken { token ->
            cartRepository.confirmOrder(token).onFailure { throw it }
        }
    }
}