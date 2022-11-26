package com.progressterra.ipbandroidview.domain.usecase.delivery

import com.progressterra.ipbandroidview.model.DeliveryMethod
import com.progressterra.ipbandroidview.model.PaymentType

interface PaymentMethodsForDeliveryUseCase {

    fun methods(methods: DeliveryMethod): Result<List<PaymentType>>

    class Base : PaymentMethodsForDeliveryUseCase {

        override fun methods(methods: DeliveryMethod): Result<List<PaymentType>> = runCatching {
            listOf(PaymentType.CASH)
        }
    }
}