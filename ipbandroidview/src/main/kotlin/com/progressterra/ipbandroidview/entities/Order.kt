package com.progressterra.ipbandroidview.entities

data class Order(
    override val id: String,
    val itemsIds: List<String>,
    val price: SimplePrice,
    val number: String,
    val status: String
) : Id
