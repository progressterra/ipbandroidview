package com.progressterra.ipbandroidview.model

data class PickUpPointInfo(
    val address: String,
    val site: String,
    val workHour: String,
    val latitude: Double,
    val longitude: Double,
    val pickupPointCode: String,
    val type: String,
    val fittingAvailable: Boolean,
    val sendingParcels: Boolean,
    val path: String,
    val deliveryPaymentType: DeliveryPaymentType,
    val nearMetroStation: String
)

enum class DeliveryPaymentType {
    PAYMENT_NOT_AVAILABLE,
    CASH,
    CARD,
    ALL_PAYMENT_TYPE
}