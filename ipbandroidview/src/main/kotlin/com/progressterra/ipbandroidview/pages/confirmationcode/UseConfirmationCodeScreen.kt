package com.progressterra.ipbandroidview.pages.confirmationcode

import com.progressterra.ipbandroidview.features.code.CodeEvent
import com.progressterra.ipbandroidview.features.code.UseCode
import com.progressterra.ipbandroidview.features.countdown.CountdownEvent
import com.progressterra.ipbandroidview.features.countdown.UseCountDown
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton

interface UseConfirmationCodeScreen : UseCode, UseCountDown, UseTopBar, UseButton {

    class Empty : UseConfirmationCodeScreen {

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: CodeEvent) = Unit

        override fun handle(event: CountdownEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit
    }
}