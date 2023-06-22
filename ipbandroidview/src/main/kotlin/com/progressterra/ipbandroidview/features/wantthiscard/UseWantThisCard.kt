package com.progressterra.ipbandroidview.features.wantthiscard

import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.counter.UseCounter

interface UseWantThisCard : UseCounter {

    fun handle(event: WantThisCardEvent)

    class Empty : UseWantThisCard {

        override fun handle(event: WantThisCardEvent) = Unit

        override fun handle(event: CounterEvent) = Unit
    }
}