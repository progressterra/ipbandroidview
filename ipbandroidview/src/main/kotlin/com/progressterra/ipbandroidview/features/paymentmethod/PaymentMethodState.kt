package com.progressterra.ipbandroidview.features.paymentmethod

import com.progressterra.ipbandroidview.entities.PaymentType


data class PaymentMethodState(
    val selectedPaymentMethod: PaymentType = PaymentType.Empty,
    val paymentMethods: List<PaymentType> = emptyList()
) {
    companion object
}