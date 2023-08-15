package com.progressterra.ipbandroidview.pages.bankcards

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.features.bankcard.BankCardState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class BankCardsScreenState(
    val add: ButtonState = ButtonState(id = "add"),
    val cards: Flow<PagingData<BankCardState>> = emptyFlow(),
    val screen: ScreenState = ScreenState.LOADING
)