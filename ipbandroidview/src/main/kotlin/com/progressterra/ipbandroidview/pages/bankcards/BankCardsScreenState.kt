package com.progressterra.ipbandroidview.pages.bankcards

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.features.bankcard.BankCardState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class BankCardsScreenState(
    val add: ButtonState = ButtonState(id = "add"),
    val addedCards: Flow<PagingData<BankCardState>> = emptyFlow(),
    val otherCards: Flow<PagingData<BankCardState>> = emptyFlow(),
    val screen: StateColumnState = StateColumnState()
)