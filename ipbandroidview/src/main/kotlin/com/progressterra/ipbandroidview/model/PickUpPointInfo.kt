package com.progressterra.ipbandroidview.model

data class PickUpPointInfo(
    val id: DeliveryMethodId = DeliveryMethodId.EMPTY,
    val price: SimplePrice = SimplePrice(),
    val address: String = "",
    val workHour: String = "",
    val latitude: Double = 0.0,
    val type: String = "",
    val longitude: Double = 0.0,
    val pickupPointCode: String = "",
    val path: String = ""
)