package com.progressterra.ipbandroidview.pages.organizations

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn

interface UseOrganizationsScreen : UseStateColumn, UseTopBar {

    fun handle(event: OrganizationsScreenEvent)

    class Empty : UseOrganizationsScreen {

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: OrganizationsScreenEvent) = Unit

        override fun handle(event: StateColumnEvent) = Unit
    }
}