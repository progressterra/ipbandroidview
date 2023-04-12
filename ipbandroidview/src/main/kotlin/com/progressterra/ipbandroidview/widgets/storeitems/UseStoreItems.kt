package com.progressterra.ipbandroidview.widgets.storeitems

import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.features.storecard.UseStoreCard
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent

interface UseStoreItems : UseStoreCard {

    class Empty : UseStoreItems {

        override fun handle(event: StoreCardEvent) = Unit

        override fun handle(event: CounterEvent) = Unit
    }
}