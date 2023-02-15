package com.progressterra.ipbandroidview.model

import androidx.compose.runtime.Immutable

sealed class Delivery {

    abstract val id: DeliveryMethodId

    abstract val available: Boolean

    abstract val price: SimplePrice

    abstract val date: String

    abstract val type: String

    data class CourierDelivery(
        override val id: DeliveryMethodId,
        override val date: String = "",
        override val price: SimplePrice = SimplePrice(),
        override val type: String = "",
        val commentary: String = ""
    ) : Delivery() {

        override val available: Boolean = true
    }

    @Immutable
    data class PickUpPointDelivery(
        override val date: String = "",
        val points: List<PickUpPointInfo>,
        val currentPoint: PickUpPointInfo
    ) : Delivery() {

        override val type: String = currentPoint.type

        override val price: SimplePrice = currentPoint.price

        override val available: Boolean = points.isNotEmpty()

        override val id: DeliveryMethodId = currentPoint.id
    }
}
