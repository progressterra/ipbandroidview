package com.progressterra.ipbandroidview.pages.bonusesdetails

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.bonuses.BonusesState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.widgets.bonusestransactions.BonusesTransactionsState

@Immutable
data class BonusesDetailsState(
    val bonusesInfo: BonusesState = BonusesState(),
    val transactions: BonusesTransactionsState = BonusesTransactionsState(),
    val screen: StateColumnState = StateColumnState()
)
