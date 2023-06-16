package com.progressterra.ipbandroidview.pages.locationpermission

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton

interface UseLocationPermissionScreen : UseTopBar, UseButton {

    class Empty : UseLocationPermissionScreen {

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: ButtonEvent) = Unit
    }
}