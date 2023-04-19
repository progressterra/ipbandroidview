package com.progressterra.ipbandroidview.pages.pickuppoint

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.widgets.pickupchoose.PickUpChooseEvent
import com.progressterra.ipbandroidview.widgets.pickupchoose.UsePickUpChoose

interface UsePickUpPoint : UseButton, UsePickUpChoose, UseTopBar {

    class Empty : UsePickUpPoint {

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: PickUpChooseEvent) = Unit
    }
}