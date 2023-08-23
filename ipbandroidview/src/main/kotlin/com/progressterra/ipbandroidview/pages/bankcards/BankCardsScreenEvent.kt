package com.progressterra.ipbandroidview.pages.bankcards

import com.progressterra.ipbandroidview.entities.Document

sealed class BankCardsScreenEvent {

    data class OpenDetails(val state: Document) : BankCardsScreenEvent()

    data object Back : BankCardsScreenEvent()
}