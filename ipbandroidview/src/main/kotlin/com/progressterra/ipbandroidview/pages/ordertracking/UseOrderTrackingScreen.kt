package com.progressterra.ipbandroidview.pages.ordertracking

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar

interface UseOrderTrackingScreen : UseTopBar {

    class Empty : UseOrderTrackingScreen {

        override fun handle(event: TopBarEvent) = Unit
    }
}