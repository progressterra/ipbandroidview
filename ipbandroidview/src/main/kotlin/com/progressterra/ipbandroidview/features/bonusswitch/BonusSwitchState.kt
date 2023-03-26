package com.progressterra.ipbandroidview.features.bonusswitch

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.proshkabonuses.ProshkaBonusesState
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchState

@Immutable
data class BonusSwitchState(
    val availableBonuses: Int = 0,
    val useBonuses: BrushedSwitchState = BrushedSwitchState(),
)