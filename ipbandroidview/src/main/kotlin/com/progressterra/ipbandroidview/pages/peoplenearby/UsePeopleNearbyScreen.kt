package com.progressterra.ipbandroidview.widgets.peoplenearby

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar

interface UsePeopleNearbyScreen : UseTopBar {

    fun handle(event: PeopleNearbyScreenEvent)

    class Empty : UsePeopleNearbyScreen {

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: PeopleNearbyScreenEvent) = Unit
    }
}