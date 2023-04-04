package com.progressterra.ipbandroidview.features.paymentmethod

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.payment.PaymentType

@Immutable
data class PaymentMethodState(
    val selectedPaymentMethod: PaymentType = PaymentType.Empty,
    val paymentMethods: List<PaymentType> = emptyList()
) {

    fun updatePaymentMethod(paymentMethod: PaymentType) =
        copy(selectedPaymentMethod = paymentMethod)
}