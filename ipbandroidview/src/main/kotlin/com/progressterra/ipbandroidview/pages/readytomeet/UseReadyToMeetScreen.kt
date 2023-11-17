package com.progressterra.ipbandroidview.pages.readytomeet

import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn

interface UseReadyToMeetScreen : UseStateColumn, UseButton, UseTopBar {

    fun handle(event: ReadyToMeetScreenEvent)
}