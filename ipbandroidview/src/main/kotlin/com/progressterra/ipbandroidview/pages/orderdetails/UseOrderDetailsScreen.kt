package com.progressterra.ipbandroidview.pages.orderdetails

import com.progressterra.ipbandroidview.features.ordercard.OrderCardEvent
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsEvent
import com.progressterra.ipbandroidview.features.orderdetails.UseOrderDetails
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar

interface UseOrderDetailsScreen : UseTopBar, UseOrderDetails {

    class Empty : UseOrderDetailsScreen {

        override fun handle(event: OrderDetailsEvent) = Unit

        override fun handle(event: OrderCardEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit
    }
}