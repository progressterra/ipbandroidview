package com.progressterra.ipbandroidview.pages.payment

sealed class PaymentEvent {

    object Back : PaymentEvent()

    object Next : PaymentEvent()
}
