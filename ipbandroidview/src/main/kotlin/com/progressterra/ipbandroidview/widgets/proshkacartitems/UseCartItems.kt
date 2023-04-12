package com.progressterra.ipbandroidview.widgets.proshkacartitems

import com.progressterra.ipbandroidview.features.proshkacartcard.ProshkaCartCardEvent
import com.progressterra.ipbandroidview.features.proshkacartcard.UseCartCard
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent

interface UseCartItems : UseCartCard {

    class Empty : UseCartItems {

        override fun handle(event: CounterEvent) = Unit

        override fun handle(event: ProshkaCartCardEvent) = Unit
    }
}