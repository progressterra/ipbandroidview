package com.progressterra.ipbandroidview.pages.wantthisrequests

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.features.wantthiscard.UseWantThisCard
import com.progressterra.ipbandroidview.features.wantthiscard.WantThisCardEvent
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox

interface UseWantThisRequests : UseStateBox, UseTopBar, UseWantThisCard {

    class Empty : UseWantThisRequests {

        override fun handle(event: WantThisCardEvent) = Unit

        override fun handle(event: CounterEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: StateBoxEvent) = Unit
    }
}