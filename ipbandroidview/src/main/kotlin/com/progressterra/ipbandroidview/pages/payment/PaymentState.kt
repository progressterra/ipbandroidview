package com.progressterra.ipbandroidview.pages.payment

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.entities.payment.PaymentType
import com.progressterra.ipbandroidview.features.bonusswitch.BonusSwitchState
import com.progressterra.ipbandroidview.features.paymentmethod.PaymentMethodState
import com.progressterra.ipbandroidview.features.receipt.ReceiptState
import com.progressterra.ipbandroidview.features.receivereceipt.ReceiveReceiptState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState

@Immutable
data class PaymentState(
    val stateBox: StateBoxState = StateBoxState(),
    val paymentMethod: PaymentMethodState = PaymentMethodState(),
    val bonusSwitch: BonusSwitchState = BonusSwitchState(),
    val receiveReceipt: ReceiveReceiptState = ReceiveReceiptState(),
    val receipt: ReceiptState = ReceiptState()
) {

    fun updateStateBoxState(screenState: ScreenState) =
        copy(stateBox = stateBox.updateState(screenState))

    fun updatePaymentMethodState(paymentMethodState: PaymentMethodState) =
        copy(paymentMethod = paymentMethodState)

    fun updatePaymentMethod(paymentMethod: PaymentType) =
        copy(paymentMethod = this.paymentMethod.updatePaymentMethod(paymentMethod))
}