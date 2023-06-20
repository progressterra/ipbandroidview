package com.progressterra.ipbandroidview.widgets.deliverypicker

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.processors.IpbSubState

@Immutable
data class DeliveryPickerState(
    val addressUI: AddressUI = AddressUI(),
    val isSelected: Boolean = false,
    @IpbSubState val city: TextFieldState = TextFieldState(id = "city"),
    @IpbSubState val home: TextFieldState = TextFieldState(id = "home"),
    @IpbSubState val entrance: TextFieldState = TextFieldState(id = "entrance"),
    @IpbSubState val apartment: TextFieldState = TextFieldState(id = "apartment"),
) {

    fun uPickUpPointInfo(info: PickUpPointInfo) =
        if (selectedDeliveryMethod is Delivery.PickUpPointDelivery) {
            copy(selectedDeliveryMethod = selectedDeliveryMethod.uCurrentPoint(info))
        } else this

    fun uSelectedMethod(delivery: Delivery) = copy(selectedDeliveryMethod = delivery)
}