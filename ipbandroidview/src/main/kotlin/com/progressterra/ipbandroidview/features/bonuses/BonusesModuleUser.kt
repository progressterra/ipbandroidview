package com.progressterra.ipbandroidview.features.bonuses

import com.progressterra.ipbandroidview.shared.mvi.ModuleUser

interface BonusesModuleUser : ModuleUser<BonusesState> {

    fun onBonusesTransactions()

    fun onWithdrawal()

    fun onAddCard()
}