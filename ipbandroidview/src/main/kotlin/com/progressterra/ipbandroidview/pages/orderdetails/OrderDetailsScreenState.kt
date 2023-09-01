package com.progressterra.ipbandroidview.pages.orderdetails

import com.progressterra.ipbandroidview.features.attachablechat.AttachableChatState
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState


data class OrderDetailsScreenState(
    val details: OrderDetailsState = OrderDetailsState(),
    val dialogId: String = "",
    val chat: AttachableChatState = AttachableChatState(),
    val screen: StateColumnState = StateColumnState(id = "main")
)