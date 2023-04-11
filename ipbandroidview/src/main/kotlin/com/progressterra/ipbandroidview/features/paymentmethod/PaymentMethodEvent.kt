package com.progressterra.ipbandroidview.features.paymentmethod

import com.progressterra.ipbandroidview.entities.PaymentType

sealed class PaymentMethodEvent {

    class Select(val type: PaymentType) : PaymentMethodEvent()
}