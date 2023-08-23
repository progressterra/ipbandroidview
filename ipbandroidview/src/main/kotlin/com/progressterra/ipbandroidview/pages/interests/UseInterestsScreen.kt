package com.progressterra.ipbandroidview.pages.interests

import com.progressterra.ipbandroidview.features.interestspicker.InterestsPickerEvent
import com.progressterra.ipbandroidview.features.interestspicker.UseInterestsPicker
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn

interface UseInterestsScreen : UseInterestsPicker, UseStateColumn, UseTopBar, UseButton {

    class Empty : UseInterestsScreen {

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: InterestsPickerEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: StateColumnEvent) = Unit
    }
}