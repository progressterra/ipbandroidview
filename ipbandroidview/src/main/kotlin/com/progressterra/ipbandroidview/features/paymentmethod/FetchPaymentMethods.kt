package com.progressterra.ipbandroidview.features.paymentmethod

import com.progressterra.ipbandroidview.entities.PaymentType

interface FetchPaymentMethods {

    suspend operator fun invoke(): Result<PaymentMethodState>

    class Base : FetchPaymentMethods {

        override suspend fun invoke(): Result<PaymentMethodState> = runCatching {
            val paymentMethods = listOf(
                PaymentType.InnerBonuses
            )
            PaymentMethodState(
                selectedPaymentMethod = paymentMethods.first(),
                paymentMethods = paymentMethods
            )
        }
    }
}