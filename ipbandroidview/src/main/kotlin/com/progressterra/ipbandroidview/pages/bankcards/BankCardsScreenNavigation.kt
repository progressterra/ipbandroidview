package com.progressterra.ipbandroidview.pages.bankcards

import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.shared.mvi.OnBack

interface BankCardsScreenNavigation : OnBack {

    fun onBankCard(data: Document)
}