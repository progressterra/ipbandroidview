package com.progressterra.ipbandroidview.pages.confirmationcode

import com.progressterra.ipbandroidview.features.code.CodeEvent
import com.progressterra.ipbandroidview.features.code.UseCode
import com.progressterra.ipbandroidview.features.countdown.CountdownEvent
import com.progressterra.ipbandroidview.features.countdown.UseCountDown
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar

interface UseConfirmationCode : UseCode, UseCountDown, UseTopBar {

    class Empty : UseConfirmationCode {

        override fun handle(event: CodeEvent) = Unit

        override fun handle(event: CountdownEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit
    }
}