package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.iecommerce.model.ParamImplementBonusV3
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation

interface UseBonusesUseCase {

    suspend fun use(sum: Int): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val cartRepository: CartRepository
    ) : AbstractUseCase(scrmRepository, provideLocation), UseBonusesUseCase {

        override suspend fun use(sum: Int): Result<Unit> = runCatching {
            withToken {
                cartRepository.implementBonus(
                    it, ParamImplementBonusV3(
                        sumPaymentBonus = sum.toDouble()
                    )
                )
            }.onFailure { throw it }
        }
    }
}
