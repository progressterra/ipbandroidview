package com.progressterra.ipbandroidview.widgets.proshkastoreitems

import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCardEvent
import com.progressterra.ipbandroidview.features.proshkastorecard.UseProshkaStoreCard
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent

interface UseProshkaStoreItems : UseProshkaStoreCard {

    class Empty : UseProshkaStoreItems {

        override fun handle(event: ProshkaStoreCardEvent) = Unit

        override fun handle(event: CounterEvent) = Unit
    }
}