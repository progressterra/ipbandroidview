package com.progressterra.ipbandroidview.features.paymentmethod

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.PaymentType

@Immutable
data class PaymentMethodState(
    val selectedPaymentMethod: PaymentType = PaymentType.InnerBalance,
    val paymentMethods: List<PaymentType> = emptyList()
)