package com.progressterra.ipbandroidview.pages.orderdetails

import com.progressterra.ipbandroidview.features.ordercard.OrderCardEvent
import com.progressterra.ipbandroidview.features.attachablechat.AttachableChatEvent
import com.progressterra.ipbandroidview.features.attachablechat.UseAttachableChat
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsEvent
import com.progressterra.ipbandroidview.features.orderdetails.UseOrderDetails
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

interface UseOrderDetailsScreen : UseTopBar, UseOrderDetails, UseStateColumn, UseAttachableChat {

    class Empty : UseOrderDetailsScreen {

        override fun handle(event: AttachableChatEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit

        override fun handle(event: StateColumnEvent) = Unit

        override fun handle(event: OrderDetailsEvent) = Unit

        override fun handle(event: OrderCardEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit
    }
}