package com.progressterra.ipbandroidview.features.bonuses

interface UseBonuses {

    fun handle(event: BonusesEvent)

    class Empty : UseBonuses {
        override fun handle(event: BonusesEvent) = Unit
    }
}