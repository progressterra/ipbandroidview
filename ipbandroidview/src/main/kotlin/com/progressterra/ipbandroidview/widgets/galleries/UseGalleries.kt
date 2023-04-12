package com.progressterra.ipbandroidview.widgets.galleries

import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.features.storecard.UseStoreCard
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent

interface UseGalleries : UseStoreCard {

    class Empty : UseGalleries {

        override fun handle(event: CounterEvent) = Unit

        override fun handle(event: StoreCardEvent) = Unit
    }
}