package com.progressterra.ipbandroidview.pages.bankcarddetails

import androidx.annotation.StringRes

sealed class BankCardDetailsScreenEffect {

    data object Back : BankCardDetailsScreenEffect()

    class OpenPhoto(val data: String) : BankCardDetailsScreenEffect()

    class Toast(@StringRes val data: Int) : BankCardDetailsScreenEffect()
}