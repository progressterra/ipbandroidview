package com.progressterra.ipbandroidview.pages.withdrawal

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.withdrawaltransaction.WithdrawalTransactionState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumnState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class WithdrawalScreenState(
    val add: ButtonState = ButtonState(id = "add"),
    val canBeWithdrawal: SimplePrice = SimplePrice(),
    val transactions: Flow<PagingData<WithdrawalTransactionState>> = emptyFlow(),
    val screen: StateColumnState = StateColumnState()
)