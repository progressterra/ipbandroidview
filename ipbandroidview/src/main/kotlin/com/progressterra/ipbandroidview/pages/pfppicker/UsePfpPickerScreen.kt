package com.progressterra.ipbandroidview.pages.pfppicker

import com.progressterra.ipbandroidview.features.pfppicker.PfpPickerEvent
import com.progressterra.ipbandroidview.features.pfppicker.UsePfpPicker
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn

interface UsePfpPickerScreen : UsePfpPicker, UseButton, UseTopBar, UseStateColumn {

    fun handle(event: PfpPickerScreenEvent)

    class Empty : UsePfpPickerScreen {

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: StateColumnEvent) = Unit

        override fun handle(event: PfpPickerEvent) = Unit

        override fun handle(event: PfpPickerScreenEvent) = Unit
    }
}