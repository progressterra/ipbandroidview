package com.progressterra.ipbandroidview.features.storecard

import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.counter.UseCounter

interface UseStoreCard : UseCounter {

    fun handle(event: StoreCardEvent)

    class Empty : UseStoreCard {

        override fun handle(event: CounterEvent) = Unit

        override fun handle(event: StoreCardEvent) = Unit
    }
}