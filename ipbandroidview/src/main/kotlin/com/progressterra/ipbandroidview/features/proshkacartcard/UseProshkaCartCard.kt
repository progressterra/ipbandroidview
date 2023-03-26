package com.progressterra.ipbandroidview.features.proshkacartcard

import com.progressterra.ipbandroidview.shared.ui.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.UseCounter

interface UseProshkaCartCard : UseCounter {

    fun handleEvent(id: String, event: ProshkaCartCardEvent)

    class Empty : UseProshkaCartCard {

        override fun handleEvent(id: String, event: CounterEvent) = Unit

        override fun handleEvent(id: String, event: ProshkaCartCardEvent) = Unit
    }
}