package com.progressterra.ipbandroidview.entities.payment

import androidx.annotation.StringRes
import com.progressterra.ipbandroidview.R

sealed class PaymentType(@StringRes val paymentName: Int) {

    object Empty : PaymentType(0)

    object CardOnline : PaymentType(R.string.payment_proshka_card_online)
    object CardOffline : PaymentType(R.string.payment_proshka_card_offline)
    object Sbp : PaymentType(R.string.payment_proshka_sbp)
    object SberPay : PaymentType(R.string.payment_proshka_sberpay)
    object Cash : PaymentType(R.string.cash)
}