package com.progressterra.ipbandroidview.ui.bonuses

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.bonuses.BonusesInfo
import com.progressterra.ipbandroidview.model.bonuses.Transaction

@Immutable
data class BonusesState(
    val bonusesInfo: BonusesInfo = BonusesInfo(),
    val transactions: List<Transaction> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING
)
