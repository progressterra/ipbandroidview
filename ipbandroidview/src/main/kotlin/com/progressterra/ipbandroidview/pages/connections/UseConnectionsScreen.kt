package com.progressterra.ipbandroidview.pages.connections

import com.progressterra.ipbandroidview.features.avatar.AvatarEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox
import com.progressterra.ipbandroidview.widgets.connections.UseConnections

interface UseConnectionsScreen : UseConnections, UseTopBar, UseStateBox {

    class Empty : UseConnectionsScreen {

        override fun handle(event: AvatarEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: StateBoxEvent) = Unit
    }
}