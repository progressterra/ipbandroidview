package com.progressterra.ipbandroidview.pages.orderdetails

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.features.attachablechat.AttachableChatState
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsState
import com.progressterra.ipbandroidview.shared.ScreenState

@Immutable
data class OrderDetailsScreenState(
    val details: OrderDetailsState,
    override val id: String,
    val dialogId: String,
    val chat: AttachableChatState,
    val screenState: ScreenState
) : Id {

    companion object
}