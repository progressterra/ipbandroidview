package com.progressterra.ipbandroidview.entities

import androidx.compose.runtime.Immutable

@Immutable
sealed class Delivery {

    abstract val id: DeliveryMethodId

    abstract val available: Boolean

    abstract val price: SimplePrice

    abstract val type: String

    @Immutable
    data class CourierDelivery(
        override val price: SimplePrice = SimplePrice(),
        override val type: String = "",
    ) : Delivery() {

        override val id = DeliveryMethodId.COURIER

        override val available: Boolean = true
    }

    @Immutable
    data class PickUpPointDelivery(
        val points: List<PickUpPointInfo>,
        val currentPoint: PickUpPointInfo,
    ) : Delivery() {

        override val type: String = currentPoint.type

        override val price: SimplePrice = currentPoint.price

        override val available: Boolean = points.isNotEmpty()

        override val id: DeliveryMethodId = currentPoint.id
    }
}
