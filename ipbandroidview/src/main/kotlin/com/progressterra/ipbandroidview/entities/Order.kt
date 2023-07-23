package com.progressterra.ipbandroidview.entities

import com.progressterra.ipbandroidapi.api.cart.models.TypeStatusOrder
import com.progressterra.ipbandroidview.features.ordercard.OrderCardState
import com.progressterra.ipbandroidview.features.ordercompact.OrderCompactState
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsState
import com.progressterra.ipbandroidview.features.ordertracking.OrderTrackingState
import com.progressterra.ipbandroidview.widgets.orderitems.OrderItemsState

data class Order(
    override val id: String,
    val itemsIds: List<String>,
    val price: SimplePrice,
    val number: String,
    val status: TypeStatusOrder,
    val date: String
) : Id {

    fun toOrderDetailsState(goods: List<OrderCardState>) =
        OrderDetailsState(
            id = id,
            number = number,
            goods = OrderItemsState(goods),
            status = status,
            count = itemsIds.size,
            totalPrice = price,
            date = date
        )

    fun toOrderCompactState() =
        OrderCompactState(
            id = id,
            number = number,
            status = status,
            count = itemsIds.size,
            totalPrice = price,
            date = date
        )

    fun toOrderTrackingState() = OrderTrackingState(
        status = status,
        number = number
    )
}
