package com.progressterra.ipbandroidview.pages.overview

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn

interface UseOverviewScreen : UseStateColumn, UseTopBar {

    fun handle(event: OverviewEvent)

    class Empty : UseOverviewScreen {

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: StateColumnEvent) = Unit

        override fun handle(event: OverviewEvent) = Unit
    }
}