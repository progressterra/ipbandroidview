package com.progressterra.ipbandroidview.pages.payment

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.shared.throwOnFailure
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.processes.location.ProvideLocation

interface ConfirmOrderUseCase {

    suspend operator fun invoke(): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val cartRepository: CartRepository
    ) : ConfirmOrderUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<Unit> = withToken { token ->
            cartRepository.confirmOrder(token).throwOnFailure()
        }
    }
}