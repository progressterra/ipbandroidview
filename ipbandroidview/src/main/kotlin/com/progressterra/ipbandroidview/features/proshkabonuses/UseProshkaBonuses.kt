package com.progressterra.ipbandroidview.features.proshkabonuses

interface UseProshkaBonuses {

    fun handle(event: ProshkaBonusesEvent)

    class Empty : UseProshkaBonuses {
        override fun handle(event: ProshkaBonusesEvent) = Unit
    }
}