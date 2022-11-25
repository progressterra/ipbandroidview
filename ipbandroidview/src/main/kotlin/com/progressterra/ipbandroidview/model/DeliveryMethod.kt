package com.progressterra.ipbandroidview.model

import com.progressterra.ipbandroidapi.api.ipbdelivery.models.DeliveryMethondTypeEnum

data class DeliveryMethod(
    val pickUpPointInfo: PickUpPointInfo?,
    val type: DeliveryMethodAddressId,
    val deliveryTime: String,
    val price: SimplePrice,
    val deliveryTypeText: String,
    val addressNeeded: Boolean = type.addressNeeded()
)

enum class DeliveryMethodAddressId(val value: String) {
    COURIER("91c316b0-3b22-4f6f-a006-36ba5331a08c"),
    PVZ("f10dc8a0-1ebf-40be-b6a6-c85cc48f2625"),
    POSTAMAT("70d5a466-d4b1-47ab-9304-cba271f613b3");

    fun addressNeeded(): Boolean = when (this) {
        COURIER -> true
        PVZ -> false
        POSTAMAT -> false
    }

    fun toServiceEnum(): DeliveryMethondTypeEnum = when (this) {
        COURIER -> DeliveryMethondTypeEnum.ZERO
        PVZ -> DeliveryMethondTypeEnum.ONE
        POSTAMAT -> DeliveryMethondTypeEnum.TWO
    }
}

fun String.toAddressId(): DeliveryMethodAddressId =
    when (this) {
        "Доставка до Пункта Выдачи Заказов (ПВЗ)" -> DeliveryMethodAddressId.PVZ
        "Доставка до постомата" -> DeliveryMethodAddressId.POSTAMAT
        else -> DeliveryMethodAddressId.COURIER
    }