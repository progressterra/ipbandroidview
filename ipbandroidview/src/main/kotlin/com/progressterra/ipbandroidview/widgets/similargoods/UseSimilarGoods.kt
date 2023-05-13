package com.progressterra.ipbandroidview.widgets.similargoods

import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.features.storecard.UseStoreCard
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.widgets.galleries.UseGalleries

interface UseSimilarGoods : UseStoreCard {

    class Empty : UseGalleries {

        override fun handle(event: CounterEvent) = Unit

        override fun handle(event: StoreCardEvent) = Unit
    }
}