package com.progressterra.ipbandroidview.pages.avatarpicker

import com.progressterra.ipbandroidview.features.avatarpicker.AvatarPickerEvent
import com.progressterra.ipbandroidview.features.avatarpicker.UseAvatarPicker
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn

interface UseAvatarPickerScreen : UseAvatarPicker, UseTopBar, UseStateColumn, UseButton {

    class Empty : UseAvatarPickerScreen {

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: AvatarPickerEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: StateColumnEvent) = Unit
    }
}