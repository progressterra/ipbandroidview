package com.progressterra.ipbandroidview.pages.bonusesdetails

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.shared.throwOnFailure
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface CancelUseBonusesUseCase {

    suspend operator fun invoke(): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: CartRepository
    ) : AbstractTokenUseCase(scrmRepository, provideLocation), CancelUseBonusesUseCase {

        override suspend fun invoke(): Result<Unit> = withToken { token ->
            repo.cancelBonuses(token).throwOnFailure()
        }
    }
}