package com.progressterra.ipbandroidview.pages.payment

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.bonusswitch.BonusSwitchState
import com.progressterra.ipbandroidview.features.paymentmethod.PaymentMethodState
import com.progressterra.ipbandroidview.features.receipt.ReceiptState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumnState

@Immutable
data class PaymentState(
    val screen: StateColumnState = StateColumnState(),
    val paymentMethod: PaymentMethodState = PaymentMethodState(),
    val bonusSwitch: BonusSwitchState = BonusSwitchState(),
    val receipt: ReceiptState = ReceiptState()
) {
    companion object
}