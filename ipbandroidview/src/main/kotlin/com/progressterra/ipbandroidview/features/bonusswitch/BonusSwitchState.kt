package com.progressterra.ipbandroidview.features.bonusswitch

import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchState


data class BonusSwitchState(
    val availableBonuses: Int = 0,
    val useBonuses: BrushedSwitchState = BrushedSwitchState(
        "useBonuses"
    ),
)