package com.progressterra.ipbandroidview.pages.occupacion

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn

interface UseOccupationScreen : UseStateColumn, UseTopBar, UseButton {

    fun handle(event: OccupationScreenEvent)

    class Empty : UseOccupationScreen {

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: OccupationScreenEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: StateColumnEvent) = Unit
    }
}