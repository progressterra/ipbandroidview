package com.progressterra.ipbandroidview.features.orderdetails

import com.progressterra.ipbandroidapi.api.cart.models.TypeStatusOrder
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.ordertracking.OrderTrackingState
import com.progressterra.ipbandroidview.widgets.orderitems.OrderItemsState

data class OrderDetailsState(
    val id: String = "",
    val number: String = "",
    val status: TypeStatusOrder = TypeStatusOrder.CANCELED,
    val date: String = "",
    val count: Int = 0,
    val totalPrice: SimplePrice = SimplePrice(),
    val goods: OrderItemsState = OrderItemsState()
) {

    fun toOrderTrackingState() = OrderTrackingState(
        status = status,
        number = number
    )

    companion object
}
