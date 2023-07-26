package com.progressterra.ipbandroidview.features.paymentmethod

import androidx.compose.runtime.Immutable
import arrow.optics.optics
import com.progressterra.ipbandroidview.entities.PaymentType

@Immutable
@optics
data class PaymentMethodState(
    val selectedPaymentMethod: PaymentType = PaymentType.Empty,
    val paymentMethods: List<PaymentType> = emptyList()
) {
    companion object
}