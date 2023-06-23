package com.progressterra.ipbandroidview.pages.payment

import com.progressterra.ipbandroidapi.api.ibonus.IBonusRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.features.bonusswitch.BonusSwitchState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface FetchBonusSwitchUseCase {

    suspend operator fun invoke(): Result<BonusSwitchState>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val bonusRepository: IBonusRepository
    ) : FetchBonusSwitchUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<BonusSwitchState> = withToken { token ->
            val result =
                bonusRepository.getGeneralInfo(token).getOrThrow()?.currentQuantity?.toInt() ?: 0
            BonusSwitchState(
                availableBonuses = result
            )
        }
    }
}