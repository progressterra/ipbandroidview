package com.progressterra.ipbandroidview.entities

import com.progressterra.ipbandroidapi.api.cart.models.TypeStatusOrder

data class Order(
    override val id: String,
    val itemsIds: List<String>,
    val price: SimplePrice,
    val number: String,
    val status: TypeStatusOrder,
    val date: String
) : Id
