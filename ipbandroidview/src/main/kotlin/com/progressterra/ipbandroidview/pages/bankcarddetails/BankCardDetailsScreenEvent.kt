package com.progressterra.ipbandroidview.pages.bankcarddetails

import androidx.annotation.StringRes

sealed class BankCardDetailsScreenEvent {

    data object Back : BankCardDetailsScreenEvent()

    class OpenPhoto(val image: String) : BankCardDetailsScreenEvent()

    class Toast(@StringRes val message: Int) : BankCardDetailsScreenEvent()
}