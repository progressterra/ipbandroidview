package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidview.features.bonusswitch.BonusSwitchState
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface FetchBonusSwitchUseCase {

    suspend operator fun invoke(): Result<BonusSwitchState>

    class Base(makeToastUseCase: MakeToastUseCase, manageResources: ManageResources) :
        FetchBonusSwitchUseCase, AbstractLoggingUseCase(
        makeToastUseCase, manageResources
    ) {

        override suspend fun invoke(): Result<BonusSwitchState> = handle {
            BonusSwitchState(availableBonuses = 0)
        }
    }
}