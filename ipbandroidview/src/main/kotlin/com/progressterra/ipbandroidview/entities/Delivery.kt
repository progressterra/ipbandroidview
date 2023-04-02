package com.progressterra.ipbandroidview.entities

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

@Immutable
sealed class Delivery {

    abstract val id: DeliveryMethodId

    abstract val available: Boolean

    abstract val price: SimplePrice

    abstract val type: String

    @Immutable
    data class CourierDelivery(
        override val id: DeliveryMethodId,
        override val price: SimplePrice = SimplePrice(),
        override val type: String = "",
        val city: TextFieldState = TextFieldState(),
        val home: TextFieldState = TextFieldState(),
        val entrance: TextFieldState = TextFieldState(),
        val apartment: TextFieldState = TextFieldState()
    ) : Delivery() {

        override val available: Boolean = true
    }

    @Immutable
    data class PickUpPointDelivery(
        val points: List<PickUpPointInfo>,
        val currentPoint: PickUpPointInfo,
        val address: TextFieldState = TextFieldState(),
        val selectPoint: ButtonState = ButtonState()
    ) : Delivery() {

        override val type: String = currentPoint.type

        override val price: SimplePrice = currentPoint.price

        override val available: Boolean = points.isNotEmpty()

        override val id: DeliveryMethodId = currentPoint.id
    }
}
