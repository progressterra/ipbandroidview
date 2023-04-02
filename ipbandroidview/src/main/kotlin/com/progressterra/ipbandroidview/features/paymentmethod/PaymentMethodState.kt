package com.progressterra.ipbandroidview.features.paymentmethod

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.payment.PaymentType

@Immutable
interface PaymentMethodState {

    val selectedPaymentMethod: PaymentType

    val paymentMethods: List<PaymentType>
}