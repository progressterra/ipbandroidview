package com.progressterra.ipbandroidview.features.bankcard

interface UseBankCard {

    fun handleEvent(event: BankCardEvent)

    class Empty : UseBankCard {

        override fun handleEvent(event: BankCardEvent) = Unit
    }
}