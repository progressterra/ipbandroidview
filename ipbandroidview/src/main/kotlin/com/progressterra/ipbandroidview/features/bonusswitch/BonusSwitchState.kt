package com.progressterra.ipbandroidview.features.bonusswitch

import com.progressterra.ipbandroidview.shared.ui.switch.SwitchState

data class BonusSwitchState(
    val availableBonuses: Int = 0,
    val useBonuses: SwitchState = SwitchState("useBonuses"),
)
