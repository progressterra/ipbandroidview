package com.progressterra.ipbandroidview.features.proshkacartcard

import com.progressterra.ipbandroidview.shared.ui.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.UseCounter

interface UseProshkaCartCard : UseCounter {

    fun handle(event: ProshkaCartCardEvent)

    class Empty : UseProshkaCartCard {

        override fun handle(event: CounterEvent) = Unit

        override fun handle(event: ProshkaCartCardEvent) = Unit
    }
}