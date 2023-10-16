package com.progressterra.ipbandroidview.pages.datingmain

import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchEvent
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.UseBrushedSwitch

interface UseDatingMainScreen : UseBrushedSwitch {

    fun handle(event: DatingMainScreenEvent)

    class Empty : UseDatingMainScreen {

        override fun handle(event: DatingMainScreenEvent) = Unit

        override fun handle(event: BrushedSwitchEvent) = Unit
    }
}