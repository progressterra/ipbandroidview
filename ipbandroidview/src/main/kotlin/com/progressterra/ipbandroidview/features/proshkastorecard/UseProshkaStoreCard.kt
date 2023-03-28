package com.progressterra.ipbandroidview.features.proshkastorecard

import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.counter.UseCounter

interface UseProshkaStoreCard : UseCounter {

    fun handle(event: ProshkaStoreCardEvent)

    class Empty : UseProshkaStoreCard {

        override fun handle(event: CounterEvent) = Unit

        override fun handle(event: ProshkaStoreCardEvent) = Unit
    }
}