package com.progressterra.ipbandroidview.pages.bonusesdetails

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.cart.models.IncomeDataImplementBonuses
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.throwOnFailure

interface UseBonusesUseCase {

    suspend operator fun invoke(sum: Int): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val cartRepository: CartRepository
    ) : AbstractTokenUseCase(scrmRepository, provideLocation), UseBonusesUseCase {

        override suspend fun invoke(sum: Int): Result<Unit> = withToken { token ->
            cartRepository.useBonuses(
                token, IncomeDataImplementBonuses(sum.toDouble())
            ).throwOnFailure()
        }
    }
}
