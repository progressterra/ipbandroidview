package com.progressterra.ipbandroidview.entities

import androidx.annotation.StringRes
import com.progressterra.ipbandroidview.R

sealed class PaymentType(@StringRes val paymentName: Int) {

    data object InnerBalance : PaymentType(R.string.payment_inner)

    data object YouKassa : PaymentType(R.string.payment_youkassa)
}