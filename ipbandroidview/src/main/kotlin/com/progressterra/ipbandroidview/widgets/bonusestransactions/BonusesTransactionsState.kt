package com.progressterra.ipbandroidview.widgets.bonusestransactions

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.bonustransaction.BonusTransactionState

@Immutable
data class BonusesTransactionsState(
    val transactions: List<BonusTransactionState>
)