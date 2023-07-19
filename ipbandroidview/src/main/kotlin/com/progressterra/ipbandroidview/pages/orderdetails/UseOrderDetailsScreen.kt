package com.progressterra.ipbandroidview.pages.orderdetails

import com.progressterra.ipbandroidview.features.ordercard.OrderCardEvent
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsEvent
import com.progressterra.ipbandroidview.features.orderdetails.UseOrderDetails
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox

interface UseOrderDetailsScreen : UseTopBar, UseOrderDetails, UseStateBox {

    class Empty : UseOrderDetailsScreen {

        override fun handle(event: StateBoxEvent) = Unit

        override fun handle(event: OrderDetailsEvent) = Unit

        override fun handle(event: OrderCardEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit
    }
}