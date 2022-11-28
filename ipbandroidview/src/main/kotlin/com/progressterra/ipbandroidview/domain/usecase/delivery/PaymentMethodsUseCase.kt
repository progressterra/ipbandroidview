package com.progressterra.ipbandroidview.domain.usecase.delivery

import com.progressterra.ipbandroidview.model.PaymentType

interface PaymentMethodsUseCase {

    fun methods(): Result<List<PaymentType>>

    class Base : PaymentMethodsUseCase {

        override fun methods(): Result<List<PaymentType>> = runCatching {
            listOf(PaymentType.CASH)
        }
    }
}