package com.progressterra.ipbandroidview.pages.orderdetails

import com.progressterra.ipbandroidview.features.ordercard.OrderCardEvent
import com.progressterra.ipbandroidview.features.orderchat.OrderChatEvent
import com.progressterra.ipbandroidview.features.orderchat.UseOrderChat
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsEvent
import com.progressterra.ipbandroidview.features.orderdetails.UseOrderDetails
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

interface UseOrderDetailsScreen : UseTopBar, UseOrderDetails, UseStateBox, UseOrderChat {

    class Empty : UseOrderDetailsScreen {

        override fun handle(event: OrderChatEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit

        override fun handle(event: StateBoxEvent) = Unit

        override fun handle(event: OrderDetailsEvent) = Unit

        override fun handle(event: OrderCardEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit
    }
}