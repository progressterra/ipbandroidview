package com.progressterra.ipbandroidview.pages.welcome

import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton

interface UseWelcomeScreen : UseButton {

    class Empty : UseWelcomeScreen {

        override fun handle(event: ButtonEvent) = Unit
    }
}