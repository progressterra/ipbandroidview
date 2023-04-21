package com.progressterra.ipbandroidview.pages.bonusesdetails

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.shared.throwOnFailure
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractUseCase

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