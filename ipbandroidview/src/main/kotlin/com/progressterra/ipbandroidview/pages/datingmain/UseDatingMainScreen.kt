package com.progressterra.ipbandroidview.pages.datingmain

import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchEvent
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.UseBrushedSwitch
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn

interface UseDatingMainScreen : UseBrushedSwitch, UseStateColumn {

    fun handle(event: DatingMainScreenEvent)

    class Empty : UseDatingMainScreen {

        override fun handle(event: StateColumnEvent) = Unit

        override fun handle(event: DatingMainScreenEvent) = Unit

        override fun handle(event: BrushedSwitchEvent) = Unit
    }
}