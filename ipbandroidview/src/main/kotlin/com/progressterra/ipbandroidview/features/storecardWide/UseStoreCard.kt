package com.progressterra.ipbandroidview.features.storecardwide

import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.counter.UseCounter

interface UseStoreCardWide : UseCounter {

    fun handle(event: StoreCardWideEvent)

    class Empty : UseStoreCardWide {

        override fun handle(event: CounterEvent) = Unit

        override fun handle(event: StoreCardWideEvent) = Unit
    }
}
