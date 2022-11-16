package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation

interface CancelUseBonusesUseCase {

    suspend fun cancel(): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: CartRepository
    ) : AbstractUseCase(scrmRepository, provideLocation), CancelUseBonusesUseCase {

        override suspend fun cancel(): Result<Unit> = runCatching {
            withToken { repo.cancelBonusesImplementation(it) }.onFailure { throw it }
        }
    }
}