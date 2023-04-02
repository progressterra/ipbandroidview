package com.progressterra.ipbandroidview.features.paymentmethod

import com.progressterra.ipbandroidview.entities.payment.PaymentType

sealed class PaymentMethodEvent {

    class Select(val type: PaymentType) : PaymentMethodEvent()
}