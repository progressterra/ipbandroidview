package com.progressterra.ipbandroidview.pages.bankcards

import com.progressterra.ipbandroidview.entities.Document

sealed class BankCardsScreenEffect {

    data class OpenDetails(val data: Document) : BankCardsScreenEffect()

    data object Back : BankCardsScreenEffect()
}