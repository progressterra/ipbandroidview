package com.progressterra.ipbandroidview.features.proshkastorecard

import com.progressterra.ipbandroidview.shared.ui.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.UseCounter

interface UseProshkaStoreCard : UseCounter {

    fun handle(id: String, event: ProshkaStoreCardEvent)

    class Empty : UseProshkaStoreCard {

        override fun handle(id: String, event: CounterEvent) = Unit

        override fun handle(id: String, event: ProshkaStoreCardEvent) = Unit
    }
}