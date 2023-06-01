package com.progressterra.ipbandroidview.pages.avatarpicker

import com.progressterra.ipbandroidview.features.avatarpicker.AvatarPickerEvent
import com.progressterra.ipbandroidview.features.avatarpicker.UseAvatarPicker
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox

interface UseAvatarPickerScreen : UseAvatarPicker, UseTopBar, UseStateBox, UseButton {

    class Empty : UseAvatarPickerScreen {

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: AvatarPickerEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: StateBoxEvent) = Unit
    }
}