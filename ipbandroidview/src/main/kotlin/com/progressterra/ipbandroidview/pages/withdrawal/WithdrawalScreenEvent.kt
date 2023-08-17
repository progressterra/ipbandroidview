package com.progressterra.ipbandroidview.pages.withdrawal

sealed class WithdrawalScreenEvent {

    data object New : WithdrawalScreenEvent()

    data object Back : WithdrawalScreenEvent()
}