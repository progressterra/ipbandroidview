package com.progressterra.ipbandroidview.widgets.bonusestransactions

import com.progressterra.ipbandroidview.features.bonustransaction.BonusTransactionState


data class BonusesTransactionsState(
    val transactions: List<BonusTransactionState> = emptyList()
)