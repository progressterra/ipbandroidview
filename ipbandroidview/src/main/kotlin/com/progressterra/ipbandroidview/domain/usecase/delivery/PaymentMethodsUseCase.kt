package com.progressterra.ipbandroidview.domain.usecase.delivery

import com.progressterra.ipbandroidview.model.payment.PaymentType

interface PaymentMethodsUseCase {

    suspend operator fun invoke(): Result<List<PaymentType>>

    class Base : PaymentMethodsUseCase {

        override suspend fun invoke(): Result<List<PaymentType>> = runCatching {
            listOf(PaymentType.CASH)
        }
    }
}