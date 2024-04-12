package com.progressterra.ipbandroidview.pages.datingmain

import com.progressterra.ipbandroidview.shared.ui.brushedswitch.SwitchEvent
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.UseSwitch
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn

interface UseDatingMainScreen : UseSwitch, UseStateColumn {

    fun handle(event: DatingMainScreenEvent)

    class Empty : UseDatingMainScreen {

        override fun handle(event: StateColumnEvent) = Unit

        override fun handle(event: DatingMainScreenEvent) = Unit

        override fun handle(event: SwitchEvent) = Unit
    }
}