package com.progressterra.ipbandroidview.features.proshkacartcard

import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.counter.UseCounter

interface UseCartCard : UseCounter {

    fun handle(event: ProshkaCartCardEvent)

    class Empty : UseCartCard {

        override fun handle(event: CounterEvent) = Unit

        override fun handle(event: ProshkaCartCardEvent) = Unit
    }
}