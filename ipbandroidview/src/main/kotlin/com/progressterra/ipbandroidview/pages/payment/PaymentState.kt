package com.progressterra.ipbandroidview.pages.payment

import androidx.compose.runtime.Immutable
import arrow.optics.optics
import com.progressterra.ipbandroidview.features.bonusswitch.BonusSwitchState
import com.progressterra.ipbandroidview.features.paymentmethod.PaymentMethodState
import com.progressterra.ipbandroidview.features.receipt.ReceiptState
import com.progressterra.ipbandroidview.shared.ScreenState

@Immutable
@optics
data class PaymentState(
    val screenState: ScreenState = ScreenState.LOADING,
    val paymentMethod: PaymentMethodState = PaymentMethodState(),
    val bonusSwitch: BonusSwitchState = BonusSwitchState(),
    val receipt: ReceiptState = ReceiptState()
) {
    companion object
}