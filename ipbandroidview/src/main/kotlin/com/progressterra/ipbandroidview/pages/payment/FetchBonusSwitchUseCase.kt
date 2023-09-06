package com.progressterra.ipbandroidview.pages.payment

import com.progressterra.ipbandroidview.features.bonusswitch.BonusSwitchState
import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase

interface FetchBonusSwitchUseCase {

    suspend operator fun invoke(): Result<BonusSwitchState>

    class Base : FetchBonusSwitchUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(): Result<BonusSwitchState> = handle {
            BonusSwitchState(availableBonuses = 0)
        }
    }
}