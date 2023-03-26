package com.progressterra.ipbandroidview.features.proshkabonuses

sealed class ProshkaBonusesEvent {

    object Action : ProshkaBonusesEvent()

    object Reverse : ProshkaBonusesEvent()
}