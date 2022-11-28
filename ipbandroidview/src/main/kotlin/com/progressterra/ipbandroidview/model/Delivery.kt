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
        val points: List<PickUpPointInfo>,
        val currentPoint: PickUpPointInfo
    ) : Delivery() {

        override val price: SimplePrice
            get() = TODO("Not yet implemented")

        override val date: String
            get() = TODO("Not yet implemented")

        override val type: String = currentPoint.type

        override val available: Boolean = points.isNotEmpty()

        override val id: DeliveryMethodId = currentPoint.id
    }
}
