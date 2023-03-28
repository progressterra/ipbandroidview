package com.progressterra.ipbandroidview.processes.usecase.delivery

import com.progressterra.ipbandroidview.entities.PaymentType

interface PaymentMethodsUseCase {

    suspend operator fun invoke(): Result<List<PaymentType>>

    class Base : PaymentMethodsUseCase {

        override suspend fun invoke(): Result<List<PaymentType>> = runCatching {
            listOf(PaymentType.CASH)
        }
    }
}