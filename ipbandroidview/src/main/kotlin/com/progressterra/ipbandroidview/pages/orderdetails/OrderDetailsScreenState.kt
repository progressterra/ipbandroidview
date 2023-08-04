package com.progressterra.ipbandroidview.pages.orderdetails

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.features.orderchat.OrderChatState
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsState
import com.progressterra.ipbandroidview.shared.ScreenState

@Immutable
data class OrderDetailsScreenState(
    val details: OrderDetailsState = OrderDetailsState(),
    override val id: String = "",
    val dialogId: String = "",
    val chat: OrderChatState = OrderChatState(),
    val screenState: ScreenState = ScreenState.LOADING
) : Id {

    companion object
}