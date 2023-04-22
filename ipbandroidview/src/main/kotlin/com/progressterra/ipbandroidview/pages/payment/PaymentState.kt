package com.progressterra.ipbandroidview.pages.payment

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.PaymentType
import com.progressterra.ipbandroidview.features.bonusswitch.BonusSwitchState
import com.progressterra.ipbandroidview.features.paymentmethod.PaymentMethodState
import com.progressterra.ipbandroidview.features.receipt.ReceiptState
import com.progressterra.ipbandroidview.features.receivereceipt.ReceiveReceiptState
import com.progressterra.ipbandroidview.shared.ScreenState

@Immutable
data class PaymentState(
    val screenState: ScreenState = ScreenState.LOADING,
    val paymentMethod: PaymentMethodState = PaymentMethodState(),
    val bonusSwitch: BonusSwitchState = BonusSwitchState(),
    val receiveReceipt: ReceiveReceiptState = ReceiveReceiptState(),
    val receipt: ReceiptState = ReceiptState()
) {

    fun updateEmail(newEmail: String) = copy(receiveReceipt = receiveReceipt.updateEmail(newEmail))

    fun reverseBonusSwitch() = copy(bonusSwitch = bonusSwitch.reverse())

    fun reverseReceiveReceipt() = copy(receiveReceipt = receiveReceipt.reverseReceiveReceipt())

    fun updateStateBoxState(newScreenState: ScreenState) = copy(screenState = newScreenState)

    fun updatePaymentMethodState(paymentMethodState: PaymentMethodState) =
        copy(paymentMethod = paymentMethodState)

    fun updatePaymentMethod(newPaymentMethod: PaymentType) =
        copy(paymentMethod = paymentMethod.updatePaymentMethod(newPaymentMethod))
}