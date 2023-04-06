package com.progressterra.ipbandroidview.processes.usecase.bonus

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.iecommerce.model.ParamImplementBonusV3
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.ProvideLocation
import com.progressterra.ipbandroidview.ext.throwOnFailure

interface UseBonusesUseCase {

    suspend operator fun invoke(sum: Int): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val cartRepository: CartRepository
    ) : AbstractUseCase(scrmRepository, provideLocation), UseBonusesUseCase {

        override suspend fun invoke(sum: Int): Result<Unit> = withToken { token ->
            cartRepository.implementBonus(
                token, ParamImplementBonusV3(
                    sumPaymentBonus = sum.toDouble()
                )
            ).throwOnFailure()
        }
    }
}
