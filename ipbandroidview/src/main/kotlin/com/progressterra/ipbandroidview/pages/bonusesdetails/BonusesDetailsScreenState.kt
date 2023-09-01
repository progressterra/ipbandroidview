package com.progressterra.ipbandroidview.pages.bonusesdetails

import com.progressterra.ipbandroidview.features.bonuses.BonusesState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.widgets.bonusestransactions.BonusesTransactionsState


data class BonusesDetailsScreenState(
    val bonusesInfo: BonusesState = BonusesState(),
    val transactions: BonusesTransactionsState = BonusesTransactionsState(),
    val screen: StateColumnState = StateColumnState()
)
