package com.progressterra.ipbandroidview.features.bonuses

sealed class BonusesEvent {

    data object Withdrawal : BonusesEvent()

    data object Transactions : BonusesEvent()
}