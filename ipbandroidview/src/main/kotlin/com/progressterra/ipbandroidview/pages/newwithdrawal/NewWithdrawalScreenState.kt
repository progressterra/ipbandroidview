package com.progressterra.ipbandroidview.pages.newwithdrawal

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.bankcard.BankCardState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextInputType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class NewWithdrawalScreenState(
    val add: ButtonState = ButtonState(id = "add"),
    val all: ButtonState = ButtonState(id = "all"),
    val canBeWithdrawal: SimplePrice = SimplePrice(),
    val cards: Flow<PagingData<BankCardState>> = emptyFlow(),
    val selectedCard: BankCardState = BankCardState(),
    val input: TextFieldState = TextFieldState(id = "input", type = TextInputType.NUMBER),
    val screen: StateBoxState = StateBoxState()
)