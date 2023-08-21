package com.progressterra.ipbandroidview.features.orderdetails

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidapi.api.cart.models.TypeStatusOrder
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.ordertracking.OrderTrackingState
import com.progressterra.ipbandroidview.widgets.orderitems.OrderItemsState

@Immutable
data class OrderDetailsState(
    val id: String,
    val number: String,
    val status: TypeStatusOrder,
    val date: String,
    val count: Int,
    val totalPrice: SimplePrice,
    val goods: OrderItemsState
) {

    fun toOrderTrackingState() = OrderTrackingState(
        status = status,
        number = number
    )
}
