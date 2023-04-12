package com.progressterra.ipbandroidview.pages.bonuses

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.features.bonuses.BonusesState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import com.progressterra.ipbandroidview.widgets.bonusestransactions.BonusesTransactionsState

@Immutable
data class BonusesState(
    val bonusesInfo: BonusesState = BonusesState(),
    val transactions: BonusesTransactionsState = BonusesTransactionsState(),
    val stateBoxState: StateBoxState = StateBoxState()
) {

    fun updateBonusesInfo(bonusesInfo: BonusesState) = copy(bonusesInfo = bonusesInfo)

    fun updateTransactions(transactions: BonusesTransactionsState) =
        copy(transactions = transactions)

    fun updateStateBoxState(screenState: ScreenState) =
        copy(stateBoxState = stateBoxState.updateState(screenState))
}
