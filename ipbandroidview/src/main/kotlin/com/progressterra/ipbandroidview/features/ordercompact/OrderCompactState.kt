package com.progressterra.ipbandroidview.features.ordercompact

import com.progressterra.ipbandroidapi.api.cart.models.TypeStatusOrder
import com.progressterra.ipbandroidview.entities.Price


data class OrderCompactState(
    val id: String = "",
    val number: String = "",
    val status: TypeStatusOrder = TypeStatusOrder.CANCELED,
    val date: String = "",
    val count: Int = 0,
    val totalPrice: Price = Price()
)
