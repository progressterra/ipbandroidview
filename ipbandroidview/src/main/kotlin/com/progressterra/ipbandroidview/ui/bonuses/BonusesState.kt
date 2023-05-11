package com.progressterra.ipbandroidview.ui.bonuses

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.composable.component.BonusesState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.Transaction

@Immutable
data class BonusesState(
    val bonusesInfo: BonusesState = BonusesState(),
    val transactions: List<Transaction> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING
)
