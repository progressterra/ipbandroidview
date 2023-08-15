package com.progressterra.ipbandroidview.pages.bankcards

import com.progressterra.ipbandroidview.pages.bankcarddetails.BankCardDetailsScreenState

sealed class BankCardsScreenEvent {

    data class OpenDetails(val state: BankCardDetailsScreenState) : BankCardsScreenEvent()

    data object Back : BankCardsScreenEvent()
}