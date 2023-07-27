package com.progressterra.ipbandroidview.pages.welcome

import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton

interface UseWelcome : UseButton {

    class Empty : UseWelcome {

        override fun handle(event: ButtonEvent) = Unit
    }
}