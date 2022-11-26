package com.progressterra.ipbandroidview.model

import androidx.annotation.StringRes
import com.progressterra.ipbandroidview.R

enum class PaymentType(@StringRes val paymentName: Int) {
    ONLINE_CARD(R.string.online_card),
    CASH(R.string.cash)
}