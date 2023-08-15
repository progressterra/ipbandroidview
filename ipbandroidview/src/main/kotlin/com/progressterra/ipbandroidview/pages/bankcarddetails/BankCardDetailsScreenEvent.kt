package com.progressterra.ipbandroidview.pages.bankcarddetails

sealed class BankCardDetailsScreenEvent {

    data object Back : BankCardDetailsScreenEvent()

    class OpenPhoto(val image: String) : BankCardDetailsScreenEvent()
}