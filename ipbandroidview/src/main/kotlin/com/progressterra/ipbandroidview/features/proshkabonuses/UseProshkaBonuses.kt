package com.progressterra.ipbandroidview.features.proshkabonuses

interface UseProshkaBonuses {

    fun handle(id: String, event: ProshkaBonusesEvent)

    class Empty : UseProshkaBonuses {
        override fun handle(id: String, event: ProshkaBonusesEvent) = Unit
    }
}