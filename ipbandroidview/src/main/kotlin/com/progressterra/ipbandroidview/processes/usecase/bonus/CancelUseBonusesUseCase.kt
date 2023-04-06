package com.progressterra.ipbandroidview.processes.usecase.bonus

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.ProvideLocation
import com.progressterra.ipbandroidview.ext.throwOnFailure

interface CancelUseBonusesUseCase {

    suspend operator fun invoke(): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: CartRepository
    ) : AbstractUseCase(scrmRepository, provideLocation), CancelUseBonusesUseCase {

        override suspend fun invoke(): Result<Unit> = withToken { token ->
            repo.cancelBonusesImplementation(token).throwOnFailure()
        }
    }
}