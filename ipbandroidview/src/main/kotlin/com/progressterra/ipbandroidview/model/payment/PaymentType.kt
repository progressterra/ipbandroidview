package com.progressterra.ipbandroidview.model.payment

import androidx.annotation.StringRes
import com.progressterra.ipbandroidview.R

enum class PaymentType(@StringRes val paymentName: Int) {
    CASH(R.string.cash)
}