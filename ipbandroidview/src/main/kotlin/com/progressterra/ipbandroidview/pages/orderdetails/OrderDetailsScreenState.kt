package com.progressterra.ipbandroidview.pages.orderdetails

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.attachablechat.AttachableChatState
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState

@Immutable
data class OrderDetailsScreenState(
    val details: OrderDetailsState = OrderDetailsState(),
    val dialogId: String = "",
    val chat: AttachableChatState = AttachableChatState(),
    val screen: StateBoxState = StateBoxState()
)