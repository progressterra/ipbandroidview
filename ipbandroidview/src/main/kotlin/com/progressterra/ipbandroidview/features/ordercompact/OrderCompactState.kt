package com.progressterra.ipbandroidview.features.ordercompact

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.widgets.orderitems.OrderItemsState

@Immutable
data class OrderCompactState(
    val id: String = "",
    val number: String = "",
    val status: String = "",
    val date: String = "",
    val count: String = "",
    val totalPrice: SimplePrice = SimplePrice(),
    val goods: OrderItemsState = OrderItemsState()
)
