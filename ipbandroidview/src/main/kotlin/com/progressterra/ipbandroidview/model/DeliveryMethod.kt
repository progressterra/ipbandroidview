package com.progressterra.ipbandroidview.model

data class DeliveryMethod(
    val type: DeliveryMethodAddressId,
    val deliveryTime: String,
    val price: SimplePrice,
    val deliveryTypeText: String,
    val addressNeeded: Boolean
)

enum class DeliveryMethodAddressId(val value: String) {
    COURIER("91c316b0-3b22-4f6f-a006-36ba5331a08c"),
    PVZ("f10dc8a0-1ebf-40be-b6a6-c85cc48f2625"),
    POSTAMAT("70d5a466-d4b1-47ab-9304-cba271f613b3")
}

fun String.toAddressId(): DeliveryMethodAddressId =
    when (this) {
        "Доставка до Пункта Выдачи Заказов (ПВЗ)" -> DeliveryMethodAddressId.PVZ
        "Доставка до постомата" -> DeliveryMethodAddressId.POSTAMAT
        else -> DeliveryMethodAddressId.COURIER
    }