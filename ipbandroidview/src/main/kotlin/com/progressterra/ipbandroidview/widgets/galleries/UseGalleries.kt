package com.progressterra.ipbandroidview.widgets.galleries

import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.features.storecard.UseStoreCard
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn

interface UseGalleries : UseStoreCard, UseStateColumn {

    class Empty : UseGalleries {

        override fun handle(event: StateColumnEvent) = Unit

        override fun handle(event: CounterEvent) = Unit

        override fun handle(event: StoreCardEvent) = Unit
    }
}