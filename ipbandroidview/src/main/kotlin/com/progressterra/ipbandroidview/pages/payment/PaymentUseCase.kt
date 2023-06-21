package com.progressterra.ipbandroidview.pages.payment

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.throwOnFailure

interface PaymentUseCase {

    suspend operator fun invoke(): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val cartRepository: CartRepository
    ) : PaymentUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<Unit> = withToken {
            cartRepository.paymentInternal(it).throwOnFailure()
        }
    }
}