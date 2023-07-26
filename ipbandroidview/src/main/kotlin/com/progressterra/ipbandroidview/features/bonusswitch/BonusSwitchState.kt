package com.progressterra.ipbandroidview.features.bonusswitch

import androidx.compose.runtime.Immutable
import arrow.optics.optics
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchState

@Immutable
@optics
data class BonusSwitchState(
    val availableBonuses: Int = 0,
    val useBonuses: BrushedSwitchState = BrushedSwitchState(
        "useBonuses"
    ),
) { companion object }