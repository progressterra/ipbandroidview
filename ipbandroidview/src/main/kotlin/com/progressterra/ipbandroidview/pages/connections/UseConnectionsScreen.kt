package com.progressterra.ipbandroidview.pages.connections

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn

interface UseConnectionsScreen : UseTopBar, UseStateColumn {

    fun handle(event: ConnectionsScreenEvent)

    class Empty : UseConnectionsScreen {

        override fun handle(event: ConnectionsScreenEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: StateColumnEvent) = Unit
    }
}