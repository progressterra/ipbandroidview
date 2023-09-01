package com.progressterra.ipbandroidview.pages.orderstatus

import com.progressterra.ipbandroidview.features.mainorreceipt.UseMainOrReceipt
import com.progressterra.ipbandroidview.features.ordernumber.UseOrderNumber
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent

interface UseOrderStatusScreen : UseTopBar, UseMainOrReceipt, UseOrderNumber {

    class Empty : UseOrderStatusScreen {

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: ButtonEvent) = Unit
    }
}