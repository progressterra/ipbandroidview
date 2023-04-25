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

    fun uEmail(newEmail: String) = copy(receiveReceipt = receiveReceipt.uEmail(newEmail))

    fun reverseBonusSwitch() = copy(bonusSwitch = bonusSwitch.reverse())

    fun reverseReceiveReceipt() = copy(receiveReceipt = receiveReceipt.reverseReceiveReceipt())

    fun uStateBoxState(newScreenState: ScreenState) = copy(screenState = newScreenState)

    fun uPaymentMethodState(paymentMethodState: PaymentMethodState) =
        copy(paymentMethod = paymentMethodState)

    fun uPaymentMethod(newPaymentMethod: PaymentType) =
        copy(paymentMethod = paymentMethod.uPaymentMethod(newPaymentMethod))
}