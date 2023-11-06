package com.progressterra.ipbandroidview.pages.info

import com.progressterra.ipbandroidview.features.info.UseInfo
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

interface UseInfoScreen : UseInfo, UseTopBar, UseButton {

    class Empty : UseInfoScreen {

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit
    }
}