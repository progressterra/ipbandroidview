package com.progressterra.ipbandroidview.pages.organizationaudits

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn

interface UseOrganizationAuditsScreen : UseTopBar, UseStateColumn {

    fun handle(event: OrganizationAuditsScreenEvent)

    class Empty : UseOrganizationAuditsScreen {

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: OrganizationAuditsScreenEvent) = Unit

        override fun handle(event: StateColumnEvent) = Unit
    }
}