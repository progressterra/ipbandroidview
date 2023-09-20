package com.progressterra.ipbandroidview.pages.withdrawal

sealed class WithdrawalScreenEffect {

    data object AddCard : WithdrawalScreenEffect()

    data object New : WithdrawalScreenEffect()

    data object Back : WithdrawalScreenEffect()
}