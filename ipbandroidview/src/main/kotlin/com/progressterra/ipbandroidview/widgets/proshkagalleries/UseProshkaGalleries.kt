package com.progressterra.ipbandroidview.widgets.proshkagalleries

import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCardEvent
import com.progressterra.ipbandroidview.features.proshkastorecard.UseProshkaStoreCard
import com.progressterra.ipbandroidview.shared.ui.CounterEvent

interface UseProshkaGalleries : UseProshkaStoreCard {

    class Empty : UseProshkaGalleries {

        override fun handle(id: String, event: CounterEvent) = Unit

        override fun handle(id: String, event: ProshkaStoreCardEvent) = Unit
    }
}