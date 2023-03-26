package com.progressterra.ipbandroidview.features.proshkabonuses

interface UseProshkaBonuses {

    fun handleEvent(id: String, event: ProshkaBonusesEvent)

    class Empty : UseProshkaBonuses {
        override fun handleEvent(id: String, event: ProshkaBonusesEvent) = Unit
    }
}