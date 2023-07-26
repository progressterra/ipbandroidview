package com.progressterra.ipbandroidview.widgets.bonusestransactions

import androidx.compose.runtime.Immutable
import arrow.optics.optics
import com.progressterra.ipbandroidview.features.bonustransaction.BonusTransactionState

@Immutable
@optics data class BonusesTransactionsState(
    val transactions: List<BonusTransactionState> = emptyList()
) { companion object }