package com.progressterra.ipbandroidview.widgets.proshkacartitems

import com.progressterra.ipbandroidview.features.proshkacartcard.ProshkaCartCardEvent
import com.progressterra.ipbandroidview.features.proshkacartcard.UseProshkaCartCard
import com.progressterra.ipbandroidview.shared.ui.CounterEvent

interface UseProshkaCartItems : UseProshkaCartCard {

    class Empty : UseProshkaCartItems {

        override fun handle(event: CounterEvent) = Unit

        override fun handle(event: ProshkaCartCardEvent) = Unit
    }
}